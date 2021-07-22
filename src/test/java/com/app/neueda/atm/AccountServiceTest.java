package com.app.neueda.atm;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.neueda.atm.pojo.Account;
import com.app.neueda.atm.service.AccountService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AccountServiceTest {
	
	@Autowired
	AccountService accountService;
	
	@Test
	public void getAccountByAccountNoTest() {
		String accountNo = "123456789";
		Account account = accountService.getAccountByAccountNo(accountNo);
		assertNotNull(account);
	}

}
