package com.ltp.expense_manager.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltp.expense_manager.entity.Expense;
import com.ltp.expense_manager.service.ExpenseService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    ExpenseService expenseService;

    @PostMapping("/addExpense/{personId}")
    public ResponseEntity<HttpStatus> addExpense(@RequestBody @Valid Expense expense, @PathVariable Long personId) {

        expenseService.addExpense(expense, personId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteExpense/{id}")
    public ResponseEntity<HttpStatus> deleteExpense(@PathVariable Long id) {

        expenseService.deleteExpense(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/updateExpense/{id}")
    public ResponseEntity<Expense> updateExpense(@RequestBody String title, @PathVariable Long id) {

        return new ResponseEntity<>(expenseService.updateExpense(title, id), HttpStatus.OK);
    }

    @GetMapping("/getExpense/{id}")
    public ResponseEntity<Expense> getExpense(@PathVariable Long id) {

        return new ResponseEntity<>(expenseService.getExpense(id), HttpStatus.OK);
    }

    @GetMapping("/getExpenses/{personId}")
    public ResponseEntity<List<Expense>> getExpenses(@PathVariable Long personId) {

        return new ResponseEntity<>(expenseService.getExpenses(personId), HttpStatus.OK);
    }
}
