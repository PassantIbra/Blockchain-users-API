package com.blockchain.practicum.constrains;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.blockchain.practicum.validators.TransactionsListSizeValidator;
/**
 * TransactionsListSizeConstrain interface is to add constrain on the size of transactions a user make at a time
 */
@Constraint(validatedBy = TransactionsListSizeValidator.class)
@Target({ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface TransactionsListSizeConstrain {
	
	String message() default "Transactions list cannot be empty or greater than 10 transactions for different users";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
