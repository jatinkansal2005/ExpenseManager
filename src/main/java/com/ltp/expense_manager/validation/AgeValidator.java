package com.ltp.expense_manager.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<Age,String>{
    
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        try {
            int intValue = Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
