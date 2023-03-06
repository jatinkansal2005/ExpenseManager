package com.ltp.expense_manager.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.auth0.jwt.interfaces.Payload;

import jakarta.validation.Constraint;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AmountValidator.class)
public @interface Amount {
    String message() default "The amount is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
