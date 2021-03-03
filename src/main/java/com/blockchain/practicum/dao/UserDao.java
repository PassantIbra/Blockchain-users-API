package com.blockchain.practicum.dao;

import java.util.List;
import java.util.Map;

import com.blockchain.practicum.model.Transaction;
import com.blockchain.practicum.model.User;
/**
 * UserDao interface is to be implemented by any DAO class to provide its functions .
 */
public interface UserDao {
	User createUserAccount(User user);

	String findUserById(String id);

	List<Transaction> getUserTransactions(String id);

	void autoIncrementUsersBalance();

	void makeTransactions(Map<String, Float> transactions, String id);
}
