package com.cg.iba.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.iba.dto.CurrentAccountRequestSubmitDTO;
import com.cg.iba.dto.SavingAccountRequestSubmitDTO;
import com.cg.iba.entity.Account;
import com.cg.iba.entity.SavingsAccount;
import com.cg.iba.entity.CurrentAccount;
import com.cg.iba.entity.Transaction;
import com.cg.iba.exception.DetailsNotFoundException;
import com.cg.iba.exception.InvalidAccountException;
import com.cg.iba.exception.InvalidAmountException;
import com.cg.iba.exception.InvalidDetailsException;
import com.cg.iba.exception.LowBalanceException;

@Service
public interface IAccountService {
	
	public Transaction transferMoney(long senderAccounId, long receiverAccountId, double amount,
			String username, String password) throws LowBalanceException, InvalidAccountException;
	public Transaction withdraw(long accounId, String username, String password) throws LowBalanceException;
	public Transaction deposit(long accounId, double amount) throws InvalidAccountException,InvalidAmountException;
	
	public SavingsAccount addSavingsAccount(SavingsAccount saving) throws InvalidDetailsException;
	public  CurrentAccount addCurrentAccount(CurrentAccount term) throws InvalidDetailsException;
	
	public SavingsAccount updateSavingsAccount(long accountId, SavingAccountRequestSubmitDTO savingRequestDTO) throws InvalidDetailsException;
	public  CurrentAccount updateCurrentAccount(long account_id, CurrentAccountRequestSubmitDTO currentRequestDTO) throws InvalidDetailsException;
	
	public  boolean closeSavingsAccount(SavingsAccount accountNo) throws InvalidAccountException;
	public  boolean closeCurrentAccount(CurrentAccount accountNo) throws InvalidAccountException;
	
	public  Account findAccountById(long account_id) throws InvalidAccountException;
	
	public List<Account> viewAccounts();
	
	public SavingsAccount viewSavingAcc(long accountId) throws DetailsNotFoundException ;
	public CurrentAccount viewCurrentAcc(long accountId) throws DetailsNotFoundException;

	public Account addDebitCardToAccount(long accNum, long debitCardNum) throws InvalidAccountException;
	public Account addNomineeToAccount(long nomineeId, long accNum) throws InvalidAccountException, DetailsNotFoundException,InvalidDetailsException;
	public Account addPolicyToAccount(long policyId, long accNum) throws InvalidAccountException, InvalidDetailsException;
	public Account addBeneficiaryToAccount(long beneficiaryId, long accNum) throws InvalidAccountException, InvalidDetailsException;
	public Account addTransactionToAccount(long transactionId, long accNum) throws InvalidAccountException, InvalidDetailsException;
}
