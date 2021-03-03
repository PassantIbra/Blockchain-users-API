package com.blockchain.practicum.exceptionhandling;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * InExistentTransactionReceiverException class to handle exception caused by
 * user trying to make transaction to inexistent user .
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InExistentTransactionReceiverException extends DataIntegrityViolationException {
	public InExistentTransactionReceiverException(String msg)

	{
		super(msg);
	}
}
