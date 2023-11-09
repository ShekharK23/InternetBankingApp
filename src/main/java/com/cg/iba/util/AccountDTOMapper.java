package com.cg.iba.util;

import org.springframework.stereotype.Component;

import com.cg.iba.dto.AccountDTO;
import com.cg.iba.entity.Account;

@Component
public class AccountDTOMapper {

	public Account getAccountUsingDTO(AccountDTO dto)
	{
		Account a = new Account();
		
		a.setAccountId(dto.getAccountId());
		a.setInterestRate(dto.getInterestRate());
		a.setBalance(dto.getBalance());
		a.setDateOfOpening(dto.getDateOfOpening());
		a.setCustomer(dto.getCustomer());
		a.setNominees(dto.getNominees());
		a.setBeneficiaries(dto.getBeneficiaries());
		a.setTransactions(dto.getTransactions());
		a.setDebitCard(dto.getDebitCard());
		//a.setBranch(dto.getBranch());
		//a.setAllPolicy(dto.getAllPolicy());
		//a.setAllInvestment(dto.getAllInvestment());
		
		return a;
	}
}
