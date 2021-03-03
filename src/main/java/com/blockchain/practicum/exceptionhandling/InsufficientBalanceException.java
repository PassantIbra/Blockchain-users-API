package com.blockchain.practicum.exceptionhandling;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * InsufficientBalanceException class to handle exception caused by user trying
 * to make transactions that exceeds his current balance.
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InsufficientBalanceException extends DataIntegrityViolationException {
	public InsufficientBalanceException(String msg)

	{
		super(msg);
	}
}
