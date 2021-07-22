package com.app.neueda.atm.pojo;

/**
 * @author vipin kumar
 */

public class WithdrawRequest {
	String accountNumber;	
	String pin;	
	Double amount;
	String atmNumber;
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getAtmNumber() {
		return atmNumber;
	}
	public void setAtmNumber(String atmNumber) {
		this.atmNumber = atmNumber;
	}
}