package com.cg.iba.util;

import org.springframework.stereotype.Component;

import com.cg.iba.dto.AccountAddDTO;
import com.cg.iba.entity.Account;

@Component
public class AccountAddConverter {

	public Account getAccountFromAccountDTO(AccountAddDTO dto)
	{
		Account a = new Account();
		
//		a.setAccountHolderName(dto.getAccountHolderName());
//		a.setAccountHolderAge(dto.getAccountHolderAge());
//		a.setAccountBalance(dto.getAccountBalance());
//		a.setPanNumber(dto.getPanNumber());
//		a.setAccountType(dto.getAccountType());
		
		return a;
	}
}
