package com.cg.iba.util;

import org.springframework.stereotype.Component;

import com.cg.iba.dto.SavingAccountRequestSubmitDTO;
import com.cg.iba.entity.SavingsAccount;

@Component
public class SavingsAccountRequestSubmitDTOConverter {

	public SavingsAccount setSavingAccountUsingDTO(SavingAccountRequestSubmitDTO dto)
	{
		SavingsAccount a = new SavingsAccount();
		
		a.setAccountHolderName(dto.getAccountHolderName());
		a.setPhoneNo(dto.getPhoneNo());
		a.setEmailId(dto.getEmailId());
		a.setAge(dto.getAge());
		a.setGender(dto.getGender());
		a.setInterestRate(dto.getInterestRate());
		a.setBalance(dto.getBalance());
		a.setDateOfOpening(dto.getDateOfOpening());
		a.setSavingMinBalance(dto.getSavingMinBalance());
		a.setSavingFine(dto.getSavingFine());
		
		return a;
	}
}
