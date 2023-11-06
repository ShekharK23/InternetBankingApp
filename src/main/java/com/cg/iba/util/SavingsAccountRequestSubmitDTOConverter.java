package com.cg.iba.util;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.cg.iba.dto.SavingAccountRequestSubmitDTO;
import com.cg.iba.entity.Account;
import com.cg.iba.entity.SavingsAccount;

@Component
public class SavingsAccountRequestSubmitDTOConverter {

	public SavingsAccount setAccountUsingDTO(SavingAccountRequestSubmitDTO dto)
	{
		SavingsAccount a = new SavingsAccount();
		
		a.setInterestRate(dto.getInterestRate());
		a.setBalance(dto.getBalance());
		//a.setDateOfOpening(LocalDate.now());
		a.setDateOfOpening(dto.getDateOfOpening());
		a.setSavingMinBalance(dto.getSavingMinBalance());
		a.setSavingFine(dto.getSavingFine());
		
		return a;
	}
}
