package com.ltp.expense_manager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ltp.expense_manager.entity.Expense;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long> {

}
