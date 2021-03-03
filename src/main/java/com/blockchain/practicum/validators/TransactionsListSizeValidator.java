package com.blockchain.practicum.validators;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.blockchain.practicum.constrains.TransactionsListSizeConstrain;
import com.blockchain.practicum.model.Transaction;
/**
 * TransactionsListSizevalidaor class is to validate  the size of transactions a user make to be 10 for different users at a time 
 */
public class TransactionsListSizeValidator
		implements ConstraintValidator<TransactionsListSizeConstrain, List<Transaction>> {

	private Map<String, Float> transactionsMap;

	public void initialize(TransactionsListSizeConstrain transactionsList) {
	}

	public boolean isValid(List<Transaction> transactionsList, ConstraintValidatorContext cxt) {
		
		// map input list to a hash map to checK for number of transaction for different
		// user
		transactionsMap = new HashMap<String, Float>();
		for (int i = 0; i < transactionsList.size(); i++) {
			Transaction transaction = transactionsList.get(i);
			float PrevBalance = 0;
			if (transactionsMap.containsKey(transaction.getReceiverEmail())) {
				PrevBalance = transactionsMap.get(transaction.getReceiverEmail());
			}
			transactionsMap.put(transaction.getReceiverEmail(), PrevBalance + transaction.getAmount());
		}

		if (transactionsMap.keySet().size() > 10 || transactionsMap.keySet().size()==0) {
			return false;
		}

		return true;
	}

	public Map<String, Float> getTransactionsMap() {
		return transactionsMap;
	}

	public void setTransactionsMap(Map<String, Float> transactionsMap) {
		this.transactionsMap = transactionsMap;
	}
}
