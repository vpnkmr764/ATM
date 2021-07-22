package com.app.neueda.atm;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.neueda.atm.exceptions.AccountDetailsException;
import com.app.neueda.atm.exceptions.TransactionException;
import com.app.neueda.atm.pojo.WithdrawRequest;
import com.app.neueda.atm.service.AccountService;
import com.app.neueda.atm.service.ValidatorService;

public class ValidatorServiceTest extends ATMGeneric {
	@Autowired
	ValidatorService validatorService;
	
	@Autowired
	AccountService accountService;
	
	@Test(expected = AccountDetailsException.class)
	public void validateAccountDetailsWrongAccountTest() throws AccountDetailsException {
		validatorService.validateAccountDetails("Account1", "3211");
	}
	
	@Test(expected = AccountDetailsException.class)
	public void validateAccountDetailsWrongPinTest() throws AccountDetailsException {
		validatorService.validateAccountDetails("123456789", "3211");
	}
	
	@Test(expected = AccountDetailsException.class)
	public void validateAccountDetailsBlankPinTest() throws AccountDetailsException {
		validatorService.validateAccountDetails("123456789", null);
	}
	
	@Test(expected = AccountDetailsException.class)
	public void validateAccountDetailsBlankAccountTest() throws AccountDetailsException {
		validatorService.validateAccountDetails(null, "3211");
	}	
	
	@Test
	public void validateAccountDetailsPositiveTest() throws AccountDetailsException {
		validatorService.validateAccountDetails("123456789", "1234");
	}
	
	@Test(expected =  TransactionException.class)
	public void validateWithdrawRequestGreaterRequestedAmountTest() throws AccountDetailsException, TransactionException {
		WithdrawRequest withdrawRequest = new WithdrawRequest();
		withdrawRequest.setAccountNumber("123456789");
		withdrawRequest.setAmount(11230d);
		withdrawRequest.setAtmNumber("ATM_1");
		withdrawRequest.setPin("1234");
		
		validatorService.validateWithdrawRequest(withdrawRequest);
		
		withdrawRequest.setAmount(750d);
		validatorService.validateWithdrawRequest(withdrawRequest);
		
		withdrawRequest.setAccountNumber("987654321");
		withdrawRequest.setAmount(1100d);
		withdrawRequest.setAtmNumber("ATM_1");
		withdrawRequest.setPin("4321");
		
		validatorService.validateWithdrawRequest(withdrawRequest);
	}
	
	@Test(expected =  TransactionException.class)
	public void validateWithdrawRequestLessATMAmountTest() throws AccountDetailsException, TransactionException {
		WithdrawRequest withdrawRequest = new WithdrawRequest();
		withdrawRequest.setAccountNumber("123456789");
		withdrawRequest.setAmount(750d);
		withdrawRequest.setAtmNumber("ATM_1");
		withdrawRequest.setPin("1234");
		
		accountService.withdrawAmount(withdrawRequest);
		
		withdrawRequest.setAccountNumber("987654321");
		withdrawRequest.setAmount(1100d);
		withdrawRequest.setAtmNumber("ATM_1");
		withdrawRequest.setPin("4321");
		
		validatorService.validateWithdrawRequest(withdrawRequest);
	}
	
}
