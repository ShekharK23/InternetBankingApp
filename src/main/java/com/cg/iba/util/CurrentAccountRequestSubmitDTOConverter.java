package com.cg.iba.util;

import org.springframework.stereotype.Component;

import com.cg.iba.dto.CurrentAccountRequestSubmitDTO;
import com.cg.iba.entity.CurrentAccount;

@Component
public class CurrentAccountRequestSubmitDTOConverter {

	public CurrentAccount setCurrentAccountUsingDTO(CurrentAccountRequestSubmitDTO dto)
	{
		CurrentAccount a = new CurrentAccount();
		
		a.setInterestRate(dto.getInterestRate());
		a.setBalance(dto.getBalance());
		a.setDateOfOpening(dto.getDateOfOpening());
		a.setCurrentMinBalance(dto.getCurrentMinBalance());
		a.setCurrentFine(dto.getCurrentFine());
		
		return a;
	}
}