package com.app.neueda.atm.controller;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.neueda.atm.exceptions.AccountDetailsException;
import com.app.neueda.atm.exceptions.TransactionException;
import com.app.neueda.atm.pojo.WithdrawRequest;
import com.app.neueda.atm.pojo.WithdrawResponse;
import com.app.neueda.atm.service.ATMService;
import com.app.neueda.atm.service.AccountService;
import com.app.neueda.atm.service.ValidatorService;

/**
 * @author vipin kumar
 * 
 * Controller which is responsible for handling web service calls.
 */

@RestController
public class ATMController {

	@Autowired
	AccountService accountService;

	@Autowired
	ATMService atmService;
	
	@Autowired
	ValidatorService validatorService;
	
    /* 
     * It validates provided account number and pin before 
     * calling service layer for fetching the available balance 
     * 
	*/
	@GetMapping(value = "/fetchBalance/{accountNo}/{pin}")
	public Double checkBalance(@PathVariable("accountNo") String accountNo, @PathVariable("pin") String pin)
			throws AccountDetailsException {
		validatorService.validateAccountDetails(accountNo, pin);
		return accountService.getBalance(accountNo);
	}

	 /* Input WithdrawRequest - has account no , pin , atm number and requested amount
	  * Output WithdrawResponse - has remaining balance available in account and currencies info (details of notes)
	  * 
      * It validates withdraw request before 
      * calling service layer for withdrawing the requested amount
      * 
      * throws Account detail exception if provided pin and account no are not correct
      * throws Transaction Exception where withdrawing money is not possible in cases
      * when requested amount is greater than available balance in account or money in ATM
      * and when amount is not dispensable due to required currencies not available
	*/	
	@PostMapping(value = "/dispenseMoney")
	public WithdrawResponse dispenseMoney(@RequestBody WithdrawRequest withdrawRequest)
			throws AccountDetailsException, TransactionException {

		validatorService.validateWithdrawRequest(withdrawRequest);

		Map<String, Integer> requestedAmountCurr = accountService.withdrawAmount(withdrawRequest);
		
		return createWithdrawResponse(requestedAmountCurr, 
				accountService.getAccountByAccountNo(withdrawRequest.getAccountNumber()).getBalance());

	}
	
	private WithdrawResponse createWithdrawResponse( Map<String, Integer> requestedAmountCurr , Double pendingBalance) {
		StringBuilder currenciesInfo = new StringBuilder();

		for (Entry<String, Integer> entry : requestedAmountCurr.entrySet()) {
			currenciesInfo.append(entry.getKey() + "*" + entry.getValue() + "  ");
		}
		WithdrawResponse withdrawResponse = new WithdrawResponse();
		withdrawResponse.setCurrencyInfo(currenciesInfo.toString());
		withdrawResponse.setBalance(pendingBalance);
		return withdrawResponse;
	}
}