package com.app.neueda.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.neueda.atm.pojo.ATMInfo;
@Repository
public interface ATMRepository extends JpaRepository<ATMInfo,Integer> { 
	ATMInfo getByAtmNumber(String atmNumber);
}
