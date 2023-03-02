package com.ltp.expense_manager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ltp.expense_manager.entity.Expense;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {
    Optional<List<Expense>> findByPersonId(Long personId);
}
