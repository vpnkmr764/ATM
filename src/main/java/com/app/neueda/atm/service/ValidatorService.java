package com.app.neueda.atm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.neueda.atm.exceptions.AccountDetailsException;
import com.app.neueda.atm.exceptions.TransactionException;
import com.app.neueda.atm.pojo.Account;
import com.app.neueda.atm.pojo.WithdrawRequest;

/**
 * @author vipin kumar
 * This service class is responsible for validating the provided inputs
 */

@Service
public class ValidatorService {
	
	@Autowired
	ATMService atmService;
	
	@Autowired
	AccountService accountService;
	
	public void validateAccountDetails(String accountNumber, String pin) throws AccountDetailsException {
		Account account = accountService.getAccountByAccountNo(accountNumber);
		if (account == null) {
			throw new AccountDetailsException(
					"Incorrect Account : Account not present in system for account number : " + accountNumber);
		}
		if (account.getPin() == null || !account.getPin().equals(pin)) {
			throw new AccountDetailsException("Incorrect Account detail : Check entered pin once");
		}

	}

	public void validateWithdrawRequest(WithdrawRequest withdrawRequest)
			throws AccountDetailsException, TransactionException {

		String atmNumber = withdrawRequest.getAtmNumber();
		String pin = withdrawRequest.getPin();
		String accountNumber = withdrawRequest.getAccountNumber();
		Double requestedAmount = withdrawRequest.getAmount();

		validateAccountDetails(accountNumber, pin);

		Double currentBalance = accountService.getBalance(accountNumber);
		if (withdrawRequest.getAmount() > currentBalance) {
			throw new TransactionException("Insufficient balance in your account : " + currentBalance);
		}

		Double atmAmount = atmService.getAmount(atmNumber);

		if (requestedAmount > atmAmount) {
			throw new TransactionException("Insufficient balance in ATM ");
		}
	}

}
