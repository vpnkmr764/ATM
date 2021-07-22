package com.app.neueda.atm.service;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.neueda.atm.exceptions.TransactionException;
import com.app.neueda.atm.pojo.Account;
import com.app.neueda.atm.pojo.WithdrawRequest;
import com.app.neueda.atm.repository.AccountRepository;

/**
 * @author vipin kumar
 * This service class is responsible for communicating account dao layer
 */

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepo;

	@Autowired
	ATMService atmService;

	public Account getAccountByAccountNo(String accountNo) {
		return accountRepo.findByAccountNumber(accountNo);
	}

	public Double getBalance(String accountNo) {
		return accountRepo.findByAccountNumber(accountNo).getBalance();
	}
    
	/* Input Withdraw request
	 * Output- A map having details related to notes which will be dispensed when user requests money withdrawal 
	 * */  
	
	@Transactional
	public Map<String, Integer> withdrawAmount(WithdrawRequest withdrawRequest) throws TransactionException {
		String atmNumber = withdrawRequest.getAtmNumber();
		String accountNumber = withdrawRequest.getAccountNumber();
		Double requestedAmount = withdrawRequest.getAmount();

		Map<String, Integer> requestedAmountCurr = atmService.deductAmount(atmNumber, requestedAmount);

		Account account = accountRepo.findByAccountNumber(accountNumber);
		account.setBalance(account.getBalance() - requestedAmount);
		accountRepo.save(account);

		return requestedAmountCurr;
	}
}