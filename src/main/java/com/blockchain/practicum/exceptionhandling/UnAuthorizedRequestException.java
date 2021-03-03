package com.blockchain.practicum.exceptionhandling;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * UnAuthorizedRequestException class to handle exception caused by user passed
 * invalid id in request header.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorizedRequestException extends EmptyResultDataAccessException {

	public UnAuthorizedRequestException(String msg) {
		super(msg, 1);
	}

}
