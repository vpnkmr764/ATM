package com.app.neueda.atm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.app.neueda.atm.controller.ATMController;
import com.app.neueda.atm.exceptions.AccountDetailsException;
import com.app.neueda.atm.exceptions.TransactionException;
import com.app.neueda.atm.pojo.WithdrawRequest;
import com.app.neueda.atm.pojo.WithdrawResponse;

public class ATMControllerTest extends ATMGeneric{
	@Autowired
	ATMController atmController;
	
	@Test
    public void dispenseMoneyTest() throws AccountDetailsException, TransactionException 
    {
        WithdrawRequest withdrawRequest = new WithdrawRequest();
        withdrawRequest.setAccountNumber("987654321");
        withdrawRequest.setAmount(0d);
        withdrawRequest.setAtmNumber("ATM_1");
        withdrawRequest.setPin("4321");     
        
        WithdrawResponse withdrawResponse = atmController.dispenseMoney(withdrawRequest);
        assertEquals(1230,withdrawResponse.getBalance().intValue());
        
    }
	
	@Test
    public void checkBalanceTest() throws AccountDetailsException, TransactionException 
    {  
        assertEquals(1230,atmController.checkBalance("987654321","4321"));       
    }	
}
