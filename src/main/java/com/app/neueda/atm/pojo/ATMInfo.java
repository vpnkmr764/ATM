package com.app.neueda.atm.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author vipin kumar Entity class for atm_info table
 */

@Entity(name = "atm_info")
public class ATMInfo {
	@Id
	@Column(name = "atm_id")
	Integer atmId;
	@Column(name = "atm_number")
	String atmNumber;

	Double amount;

	@OneToMany(mappedBy = "atmInfo")
	List<Currencies> currencies = new ArrayList<>();

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

	public String getAtmNumber() {
		return atmNumber;
	}

	public void setAtmNumber(String atmNumber) {
		this.atmNumber = atmNumber;
	}

	public Integer getAtmId() {
		return atmId;
	}

	public void setAtmId(Integer atmId) {
		this.atmId = atmId;
	}
}