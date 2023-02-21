package com.ltp.expense_manager.exception;

public class ExpenseNotFoundException extends RuntimeException {

    public ExpenseNotFoundException(Long id) {
        super("The id '" + id + "' does not exist in our records");
    }
}
