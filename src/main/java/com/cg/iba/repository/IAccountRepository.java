package com.cg.iba.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.iba.entity.Account;
import com.cg.iba.entity.SavingsAccount;
import com.cg.iba.entity.CurrentAccount;
import com.cg.iba.entity.Policy;
import com.cg.iba.entity.Transaction;
import com.cg.iba.exception.DetailsNotFoundException;
import com.cg.iba.exception.InvalidAccountException;
import com.cg.iba.exception.InvalidAmountException;
import com.cg.iba.exception.InvalidDetailsException;
import com.cg.iba.exception.LowBalanceException;


@Repository
public interface IAccountRepository extends JpaRepository<Account, Long>{

//	public Transaction transferMoney(long senderAccounId, long receiverAccountId, double amount,
//			String username, String password) throws LowBalanceException, InvalidAccountException;
//	public Transaction withdraw(long accounId, String username, String password) throws LowBalanceException;
//	public Transaction deposit(long accounId, double amount) throws InvalidAccountException,InvalidAmountException;
//	
//	public SavingsAccount addSavingsAccount(SavingsAccount saving) throws InvalidDetailsException;
//	public  CurrentAccount addTermAccount(CurrentAccount term) throws InvalidDetailsException;
//	
//	public SavingsAccount updateSavingsAccount(SavingsAccount saving) throws InvalidDetailsException;
//	public  CurrentAccount updateTermAccount(CurrentAccount term) throws InvalidDetailsException;
//	
//	public  boolean closeSavingsAccount(SavingsAccount accountNo) throws InvalidAccountException;
//	public  boolean closeTermAccount(CurrentAccount accountNo) throws InvalidAccountException;
//	
//	public  Account findAccountById(int account_id) throws InvalidAccountException;
//	
//	public Set<Account> viewAccounts(long customerId) throws DetailsNotFoundException;
//	
//	public SavingsAccount viewSavingAcc(long customerId) throws DetailsNotFoundException ;
//	public CurrentAccount viewTermAcc(long customerId) throws DetailsNotFoundException;
	
}
