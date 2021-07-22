package com.app.neueda.atm.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.neueda.atm.pojo.Account;
import com.app.neueda.atm.service.AccountService;

public class AccountServiceTest extends ATMGeneric {
	
	@Autowired
	AccountService accountService;
	
	@Test
	public void getAccountByAccountNoTest() {
		String accountNo = "123456789";
		Account account = accountService.getAccountByAccountNo(accountNo);
		assertNotNull(account);
	}

	@Test
	public void getBalanceTest() {
		String accountNo = "123456789";
		Double balance = accountService.getBalance(accountNo);
		assertEquals(800,balance.intValue());
	}	
	
}
