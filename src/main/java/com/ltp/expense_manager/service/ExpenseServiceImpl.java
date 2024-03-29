package com.ltp.expense_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ltp.expense_manager.entity.Expense;
import com.ltp.expense_manager.entity.Person;
import com.ltp.expense_manager.exception.EntityNotFoundException;
import com.ltp.expense_manager.repository.ExpenseRepository;
import com.ltp.expense_manager.repository.PersonRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    ExpenseRepository expenseRepository;
    PersonRepository personRepository;

    @Override
    public void addExpense(Expense expense, Long personId) {
        Person person = PersonServiceImpl.unwrapPerson(personRepository.findById(personId), personId);
        expense.setPerson(person);
        expenseRepository.save(expense);
    }

    @Override
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public Expense updateExpense(Expense expense, Long id) {
        Expense validExpense = unwrapExpense(expenseRepository.findById(id), id); 
        validExpense.setTitle(expense.getTitle());
        validExpense.setAmount(expense.getAmount());
        validExpense.setAmountDate(expense.getAmountDate());
        return expenseRepository.save(validExpense);
    }

    @Override
    public Expense getExpense(Long id) {
        return unwrapExpense(expenseRepository.findById(id), id);
    }

    @Override
    public List<Expense> getExpenses(Long personId) {
        Optional<List<Expense> > expenses = expenseRepository.findByPersonId(personId);
        if(expenses.isPresent()) {
            return expenses.get();
        }
        throw new EntityNotFoundException(personId, Person.class);
    }

    public static Expense unwrapExpense(Optional<Expense> expense, Long id) {
        if (!expense.isPresent())
            throw new EntityNotFoundException(id, Expense.class);
        return expense.get();
    }
}
