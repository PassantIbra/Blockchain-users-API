package com.blockchain.practicum.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blockchain.practicum.dao.UserDao;
import com.blockchain.practicum.model.Transaction;
import com.blockchain.practicum.model.User;
import com.blockchain.practicum.service.UserService;

@Component

public class UserServiceImp implements UserService {
	@Autowired
	UserDao userDao;

	public User createUserAccount(User user) {

		return userDao.createUserAccount(user);
	}

	public String findUserById(String id) {
		return userDao.findUserById(id);
	}

	public List<Transaction> getUserTransactions(String id) {
		return userDao.getUserTransactions(id);
	}

	public void autoIncrementUsersBalance() {
		userDao.autoIncrementUsersBalance();
		return;
	}

	public void makeTransactions(Map<String, Float> transactions, String id) {
		userDao.makeTransactions(transactions, id);
		return;
	}

}
