package com.app.neueda.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.neueda.atm.pojo.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> { 
	
	Account  findByAccountNumber(String accountNumber);
}
