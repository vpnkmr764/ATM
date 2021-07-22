package com.app.neueda.atm.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.neueda.atm.exceptions.TransactionException;
import com.app.neueda.atm.pojo.ATMInfo;
import com.app.neueda.atm.pojo.Currencies;
import com.app.neueda.atm.repository.ATMRepository;

/**
 * @author vipin kumar
 * This service class is responsible for communicating atm dao layer
 */

@Service
public class ATMService {
	@Autowired
	ATMRepository atmRepository;

	public Double getAmount(String atmId) {
		return atmRepository.getById(atmId).getAmount();
	}

	@Transactional
	public Map<String, Integer> deductAmount(String atmId, Double requestedAmount) throws TransactionException {
		ATMInfo atmInfo = atmRepository.getById(atmId);
		Map<String, Integer> requestedAmountCurr = updateCurrencies(atmInfo, requestedAmount);
		atmInfo.setAmount(atmInfo.getAmount() - requestedAmount);
		atmRepository.save(atmInfo);
		return requestedAmountCurr;
	}
	
	/* Input AtmInfo and Requested amount
	 * Output- A map having details related to notes which will be dispensed when user requests money withdrawal 
	 * 
	 * This method fetch out available denominations and its count from DB
	 * and sorts this list in descending order on basis of denomination
	 * 
	 * Then it checks whether requested amount is dispensable using available denominations
	 * and returns the map having details of notes if requested amount is dispensable
	 * else will throw exception to controller layer
	 * 
	 * */
	private Map<String, Integer> updateCurrencies(ATMInfo atmInfo, Double requestedAmount) throws TransactionException {
		Map<String, Integer> requestedAmountCurr = new HashMap<>();
		List<Currencies> currencies = atmInfo.getCurrencies();

		Collections.sort(currencies, new Comparator<Currencies>() {
			@Override
			public int compare(Currencies com1, Currencies com2) {
				return Integer.valueOf(com2.getDenomination()).compareTo(Integer.valueOf(com1.getDenomination()));
			}
		});

		for (Currencies currencies2 : currencies) {
			String denomination = currencies2.getDenomination();
			Integer totalCount = currencies2.getTotalCount();
			
			@SuppressWarnings("deprecation")
			int number = new Double(requestedAmount / Integer.valueOf(denomination)).intValue();
			
			if(number > totalCount) {
				requestedAmount = (requestedAmount - (Integer.valueOf(denomination)*totalCount));
				requestedAmountCurr.put(denomination, totalCount);
				currencies2.setTotalCount(0);
			}else {
				requestedAmount = requestedAmount % Integer.valueOf(denomination);
				requestedAmountCurr.put(denomination, number);
				currencies2.setTotalCount(currencies2.getTotalCount()-number);
			}
			
			if(requestedAmount ==0) {
				break;
			}
		}

		if (requestedAmount != 0) {
			throw new TransactionException("Amount not dispensable");
		}
		return requestedAmountCurr;
	}
}