package com.ltp.expense_manager.repository;

import org.springframework.data.repository.CrudRepository;

import com.ltp.expense_manager.entity.Expense;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {

}
