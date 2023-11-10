package com.cg.iba.util;

import org.springframework.stereotype.Component;

import com.cg.iba.dto.CurrentAccountResponseDTO;
import com.cg.iba.entity.CurrentAccount;

@Component
public class CurrentAccountResponseDTOMapper {

	public CurrentAccount getCurrentAccountUsingDTO(CurrentAccountResponseDTO dto)
	{
		CurrentAccount a = new CurrentAccount();
		
		a.setAccountId(dto.getAccountId());
		a.setAccountHolderName(dto.getAccountHolderName());
		a.setPhoneNo(dto.getPhoneNo());
		a.setEmailId(dto.getAccountHolderName());
		a.setAge(dto.getAge());
		a.setGender(dto.getGender());
		a.setInterestRate(dto.getInterestRate());
		a.setBalance(dto.getBalance());
		a.setDateOfOpening(dto.getDateOfOpening());
		a.setCurrentMinBalance(dto.getCurrentMinBalance());
		a.setCurrentFine(dto.getCurrentFine());
		
		return a;
	}
}
