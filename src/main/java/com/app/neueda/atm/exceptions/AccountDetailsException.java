package com.app.neueda.atm.exceptions;

/**
 * @author vipin kumar
 * 
 * Exception is thrown for below cases
 * 1) Account is not there in system for provided account no
 * 2) Pin is not correct
 */

public class AccountDetailsException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public AccountDetailsException(String message) {
		super(message);
	}

}
