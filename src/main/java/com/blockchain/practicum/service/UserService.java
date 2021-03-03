package com.blockchain.practicum.service;

import java.util.List;
import java.util.Map;

import com.blockchain.practicum.model.Transaction;
import com.blockchain.practicum.model.User;

public interface UserService {
	User createUserAccount(User user);
	String findUserById(String id);

	List<Transaction> getUserTransactions(String id);

	void autoIncrementUsersBalance();

	void makeTransactions(Map<String, Float> transactions, String id);
}
