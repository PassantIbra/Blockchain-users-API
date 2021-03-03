package com.blockchain.practicum.exceptionhandling;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
/**
 * 
 * CustomizedResponseEntityExceptionHandler class is for central exception handling throw the application.
 *
 */
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * handling exception caused by user trying to create new account with existed
	 * email.
	 * 
	 * @return response body timeStamp, message and description of the error with
	 *         HTTP status code of CONFLICT.
	 */
	@ExceptionHandler(DuplicateEmailException.class)
	public final ResponseEntity<Object> handleduplicateEmailException(DuplicateEmailException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(true));
		return new ResponseEntity(exceptionResponse, HttpStatus.CONFLICT);
	}

	/**
	 * handling exception caused by user trying to make transaction to inexistent
	 * user .
	 * 
	 * @return response body timeStamp, message and description of the error with
	 *         HTTP status code of NOT_FOUND.
	 */
	@ExceptionHandler(InExistentTransactionReceiverException.class)
	public final ResponseEntity<Object> handleInExistedTransactionReceiverException(
			InExistentTransactionReceiverException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	/**
	 * handling exception caused by user trying to make transactions that exceeds
	 * his current balance.
	 * 
	 * @return response body timeStamp, message and description of the error with
	 *         HTTP status code of UNPROCESSABLE_ENTITY.
	 */
	@ExceptionHandler(InsufficientBalanceException.class)
	public final ResponseEntity<Object> handleInsufficientBalanceException(InsufficientBalanceException ex,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	/**
	 * handling exception caused by user trying to pass parameters violates the
	 * setted constrains.
	 * 
	 * @return response body timeStamp, message and description of the error with
	 *         HTTP status code of BAD_REQUEST.
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity handleConstraintViolationException(ConstraintViolationException ex) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed",
				ex.getMessage().toString());
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed",
				ex.getBindingResult().toString());
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
