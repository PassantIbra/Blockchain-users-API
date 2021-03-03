package com.blockchain.practicum.exceptionhandling;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * DuplicateEmailException class to handle exception caused by user trying to
 * create new account with existed email.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateEmailException extends DataIntegrityViolationException {

	public DuplicateEmailException(String message) {
		super(message);
	}

}
