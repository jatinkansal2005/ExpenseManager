package com.ltp.expense_manager.exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(Long id){
        super("The person id" + id + "doesn't exist in our records");
    }
}
