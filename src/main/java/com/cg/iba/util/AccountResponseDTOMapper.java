package com.cg.iba.util;

import com.cg.iba.dto.AccountResponseDTO;
import com.cg.iba.entity.Account;
import com.cg.iba.entity.CurrentAccount;
import com.cg.iba.entity.SavingsAccount;

public class AccountResponseDTOMapper {

	public Account setAccountUsingDTO(AccountResponseDTO dto)
	{
		Account a = new Account();
		
		a.setAccountHolderName(dto.getAccountHolderName());
		a.setPhoneNo(dto.getPhoneNo());
		a.setEmailId(dto.getAccountHolderName());
		a.setAge(dto.getAge());
		a.setGender(dto.getGender());
		a.setInterestRate(dto.getInterestRate());
		a.setBalance(dto.getBalance());
		a.setDateOfOpening(dto.getDateOfOpening());
		
		if(a instanceof CurrentAccount) {
			CurrentAccount ca = (CurrentAccount) a;
			ca.setCurrentMinBalance(dto.getCurrentMinBalance());
			ca.setCurrentFine(dto.getCurrentFine());
		}
		
		if(a instanceof SavingsAccount) {
			SavingsAccount sa = (SavingsAccount) a;
			sa.setSavingMinBalance(dto.getSavingMinBalance());
			sa.setSavingFine(dto.getSavingFine());
		}
		return a;
	}
}
