package com.app.neueda.atm.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author vipin kumar
 * Entity class for atm_info table
 */

@Entity(name = "atm_info")
public class ATMInfo {	
	@Id
	String id;	
	Double amount;
	
	@OneToMany(mappedBy = "atmInfo")
	List<Currencies> currencies = new ArrayList<>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public List<Currencies> getCurrencies() {
		return currencies;
	}
	public void setCurrencies(List<Currencies> currencies) {
		this.currencies = currencies;
	}	
}