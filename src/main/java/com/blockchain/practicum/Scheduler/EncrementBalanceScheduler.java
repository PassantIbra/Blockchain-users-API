package com.blockchain.practicum.Scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.blockchain.practicum.service.UserService;

/**
 * The EncrementBalanceScheduler is to do the scheduled task of incrementing
 * users balance every 30 minutes
 * 
 * comments.
 */
@Component
public class EncrementBalanceScheduler {
	@Autowired
	UserService service;

	// every 30 minutes
	@Scheduled(fixedRate = 30* 60 * 1000)
	public void autoIncrementUsersBalanceSceduled() {
		service.autoIncrementUsersBalance();
	}

}
