package com.app.neueda.atm.exceptions;

/**
 * @author vipin kumar
 * 
 * Exception is thrown when withdrawing money is not possible in below cases
   1) Requested amount is greater than available balance in account or money in ATM
   2) Requested amount is greater than available money in ATM
   3) When requested amount is not dispensable due to required currencies are not available
 */

public class TransactionException extends Exception {

	private static final long serialVersionUID = 1L;

	public TransactionException(String msg) {
		super(msg);
	}

}