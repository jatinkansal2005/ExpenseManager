package com.ltp.expense_manager.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AmountValidator implements ConstraintValidator<Amount, String>{
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            Double doubleValue = Double.parseDouble(value);
            return doubleValue > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
