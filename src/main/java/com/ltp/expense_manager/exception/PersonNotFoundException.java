package com.ltp.expense_manager.exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(Long id){
        super("The id '" + id + "' does not exist in our records");
    }
}
