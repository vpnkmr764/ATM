package com.app.neueda.atm.pojo;

/**
 * @author vipin kumar
 */ 
public class WithdrawResponse {
	String currencyInfo;	// this has details of notes which will be dispensed when user requests money withdrawal	
	Double balance;
	public String getCurrencyInfo() {
		return currencyInfo;
	}
	public void setCurrencyInfo(String currencyInfo) {
		this.currencyInfo = currencyInfo;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
}
