package com.ltp.expense_manager.service;

import java.util.List;

import com.ltp.expense_manager.entity.Expense;

public interface ExpenseService {

    public void addExpense(Expense expense, Long personId);

    public void deleteExpense(Long id);

    public Expense updateExpense(String title, Long id);

    public Expense getExpense(Long id);

    public List<Expense> getExpenses(Long personId);
}
