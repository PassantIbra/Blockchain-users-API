package com.blockchain.practicum.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.blockchain.practicum.constrains.TransactionsListSizeConstrain;
import com.blockchain.practicum.exceptionhandling.DuplicateEmailException;
import com.blockchain.practicum.model.Transaction;
import com.blockchain.practicum.model.User;
import com.blockchain.practicum.service.UserService;
import com.blockchain.practicum.validators.TransactionsListSizeValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * UserRestController is the rest controller that handles all  requests.
 */

@RestController
@Validated
public class UserRestController {
	@Autowired
	private UserService service;
	@Autowired
	ObjectMapper mapper;

	/**
	 * create new user account.
	 *
	 * @param user
	 *            json request body that is binded to user object;
	 * @return object of created user with unique id in response body
	 */
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

		try {
			user = service.createUserAccount(user);

		} // email already in DB.
		catch (DataIntegrityViolationException e) {
			throw new DuplicateEmailException("email already exists");
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
				.toUri();

		return ResponseEntity.created(location).body(user);

	}

	/**
	 * retrieve user transactions either as sender or receiver.
	 *
	 * @param id
	 *            an attribute passed in request header.
	 * @return list of user transaction.
	 */
	@GetMapping("/users/{id}/transactions")
	public List<Transaction> getAllTransactions(
			 @RequestHeader(name = "id")String id) {
		List<Transaction> transactios = service.getUserTransactions(id);

		return transactios;
	}

	/**
	 * allow user to make transactions .
	 *
	 * @param id
	 *            an attribute passed in request header.
	 * @param transactionsList
	 *            list of transaction passed as json object containing email of the
	 *            user to receive the transaction and the amount of VCs to be
	 *            received -any other attributes will be ignored-.
	 * @return empty response with created http status.
	 */
	@PostMapping("/users/{id}/transactions")
	public ResponseEntity makeTransactions(
			@Valid @RequestBody @TransactionsListSizeConstrain List<Transaction> transactionsList,
			 @RequestHeader(name = "id") String id) {

		// retrieve map of transactions to minimize number of queries.
		TransactionsListSizeValidator transactionsListSizeValidator = new TransactionsListSizeValidator();
		transactionsListSizeValidator.isValid(transactionsList, null);
		service.makeTransactions(transactionsListSizeValidator.getTransactionsMap(), id);

		return new ResponseEntity(HttpStatus.CREATED);

	}

}
