package com.ltp.expense_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ltp.expense_manager.entity.Expense;
import com.ltp.expense_manager.exception.ExpenseNotFoundException;
import com.ltp.expense_manager.repository.ExpenseRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    ExpenseRepository expenseRepository;

    @Override
    public void addExpense(Expense expense) {
        expenseRepository.save(expense);
    }

    @Override
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public Expense updateExpense(String title, Long id) {
        Expense expense = unwrapExpense(expenseRepository.findById(id), id);
        expense.setTitle(title);
        return expenseRepository.save(expense);
    }

    @Override
    public Expense getExpense(Long id) {
        return unwrapExpense(expenseRepository.findById(id), id);
    }

    @Override
    public List<Expense> getExpenses() {
        return (List<Expense>) expenseRepository.findAll();
    }

    public Expense unwrapExpense(Optional<Expense> expense, Long id) {
        if (!expense.isPresent())
            throw new ExpenseNotFoundException(id);
        return expense.get();
    }
}
