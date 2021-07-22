package com.app.neueda.atm.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author vipin kumar
 * Entity class for atm_currencies table
 */

@Entity(name = "atm_currencies")
public class Currencies {   
	@Id
	Integer id;	
	String denomination;
	Integer count;
	
	@ManyToOne
	@JoinColumn(name = "atm_id")
	ATMInfo atmInfo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public ATMInfo getAtmInfo() {
		return atmInfo;
	}
	public void setAtmInfo(ATMInfo atmInfo) {
		this.atmInfo = atmInfo;
	}
	public String getDenomination() {
		return denomination;
	}
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}	

	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}