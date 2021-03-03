package com.blockchain.practicum.dao.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.blockchain.practicum.dao.UserDao;
import com.blockchain.practicum.exceptionhandling.InExistentTransactionReceiverException;
import com.blockchain.practicum.exceptionhandling.InsufficientBalanceException;
import com.blockchain.practicum.model.Transaction;
import com.blockchain.practicum.model.User;
/**
 *UserDaoImp is the implementation of UserDao interface it provides interaction with DB using JDBC.
 */
@Component
public class UserDaoImp extends JdbcDaoSupport implements UserDao {
	@Autowired
	DataSource dataSource;
	@Autowired
	TransactionTemplate transactionTemplate;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	/**
	 * create new user account.
	 *
	 * @param user
	 *            user object holding data of the user to be created.
	 * @return object of created user with unique id.
	 */
	public User createUserAccount(User user) {
		String sql = "INSERT INTO user " + "(id,name,email,password) VALUES (?,?,?,md5(?))";
		// generating uniqe id for the new user using UUID class
		String uniqueId = (UUID.randomUUID()).toString();
		getJdbcTemplate().update(sql, new Object[] { uniqueId, user.getName(), user.getEmail(), user.getPassword() });
		user.setId(uniqueId);
		return user;

	}

	/**
	 * check for user existence
	 *
	 * @param id
	 *            unique id for searched user.
	 * @return the email of the user if existed
	 */
	public String findUserById(String id) {
		String sql = "SELECT email FROM user WHERE id = ?";
		String userEmail = getJdbcTemplate().queryForObject(sql, new Object[] { id }, String.class);
		return userEmail;
	}

	/**
	 * retrieve user transactions either as sender or receiver.
	 *
	 * @param email
	 *            email of the user .
	 * @return list of user transaction either as sender or receiver.
	 */
	public List<Transaction> getUserTransactions(String id) {
		String sql ="SELECT sender_email,receiver_email,amount,date FROM practicum.transaction inner join practicum.user on transaction.sender_email=user.email "+
				"where user.id='" + id + "'"+
				" union "+
				"SELECT sender_email,receiver_email,amount,date FROM practicum.transaction inner join practicum.user on transaction.receiver_email=user.email "+
				"where user.id='" +id + "'";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

		List<Transaction> result = new ArrayList<Transaction>();
		for (Map<String, Object> row : rows) {
			Transaction transaction = new Transaction();
			transaction.setSenderEmail((String) row.get("sender_email"));
			transaction.setReceiverEmail((String) row.get("receiver_email"));
			transaction.setAmount((Float) row.get("amount"));
			transaction.setDate((Date) row.get("date"));
			result.add(transaction);
		}

		return result;

	}

	/**
	 * auto increment all users balance by .25 every 30 minutes
	 */
	public void autoIncrementUsersBalance() {
		String sql = "UPDATE user SET balance = balance + 0.25";
		String uniqueKey = (UUID.randomUUID()).toString();
		getJdbcTemplate().update(sql);
		return;

	}

	/**
	 * make transactions.
	 *
	 * @param transactions
	 *            map of user transaction having receiver email as key and amount as
	 *            value.
	 * 
	 */
	@Transactional
	public void makeTransactions(final Map<String, Float> transactions, final String id) {
		// get email of the current user;
		final String userEmail = findUserById(id);
		StringBuilder sql = new StringBuilder("");
		// excute queries as transaction eighter make them all or roll back.
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				try {
					for (Entry<String, Float> entry : transactions.entrySet()) {
						// subtracting VCs to be sent from the sender balance.
						getJdbcTemplate().update("UPDATE user SET balance=balance-'" + entry.getValue()
								+ "' WHERE email='" + userEmail + "';");
						// adding VCs to be received to the receiver balance.
						getJdbcTemplate().update("UPDATE user SET balance=balance+'" + entry.getValue()
								+ "' WHERE email='" + entry.getKey() + "';");
						// insert the transaction details
						getJdbcTemplate()
								.update("INSERT INTO transaction (`sender_email`, `receiver_email`, `amount`) VALUES ('"
										+ userEmail + "','" + entry.getKey() + "', '" + entry.getValue() + "');");

					}

				} catch (Exception ex) {

					status.setRollbackOnly();
					// user tried to send VCs to inexistent user.
					if (ex.toString().contains("foreign key constraint fails")) {
						throw new InExistentTransactionReceiverException(
								"these transactions contain some inexisted receivers, none of the transactions will be created");
					}
					// user tried to send VCs that exceeds his balance.
					else if (ex.toString().contains("Out of range value")) {
						throw new InsufficientBalanceException("your current balance is insufficient");
					}

				}
			}
		});

	}

}
