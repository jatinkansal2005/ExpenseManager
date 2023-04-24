package com.ltp.expense_manager.web;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.ltp.expense_manager.service.PersonService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/expenses")
public class ExpenseController {

    ExpenseService expenseService;
    PersonService personService;

    @PostMapping("/addExpense/{personId}")
    public ResponseEntity<Expense> addExpense(@RequestBody @Valid Expense expense, @PathVariable Long personId, Principal principal) {
        if (!personService.isValid(principal.getName(), personId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        expenseService.addExpense(expense, personId);
        return new ResponseEntity<>(expense,HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteExpense/{id}")
    public ResponseEntity<HttpStatus> deleteExpense(@PathVariable Long id) {

        expenseService.deleteExpense(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/updateExpense/{id}")
    public ResponseEntity<Expense> updateExpense(@RequestBody Expense expense, @PathVariable Long id) {

        return new ResponseEntity<>(expenseService.updateExpense(expense, id), HttpStatus.OK);
    }

    @GetMapping("/getExpense/{id}")
    public ResponseEntity<Expense> getExpense(@PathVariable Long id) {

        return new ResponseEntity<>(expenseService.getExpense(id), HttpStatus.OK);
    }

    @GetMapping("/getExpenses/{personId}")
    public ResponseEntity<List<Expense>> getExpenses(@PathVariable Long personId, Principal principal) {
        if (!personService.isValid(principal.getName(), personId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(expenseService.getExpenses(personId), HttpStatus.OK);
    }
}
