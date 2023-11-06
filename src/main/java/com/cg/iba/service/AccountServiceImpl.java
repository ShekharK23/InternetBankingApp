package com.cg.iba.service;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.iba.dto.CurrentAccountRequestSubmitDTO;
import com.cg.iba.dto.SavingAccountRequestSubmitDTO;
import com.cg.iba.entity.Account;
import com.cg.iba.entity.CurrentAccount;
import com.cg.iba.entity.SavingsAccount;
import com.cg.iba.entity.Transaction;
import com.cg.iba.exception.DetailsNotFoundException;
import com.cg.iba.exception.InvalidAccountException;
import com.cg.iba.exception.InvalidAmountException;
import com.cg.iba.exception.InvalidDetailsException;
import com.cg.iba.exception.LowBalanceException;
import com.cg.iba.repository.IAccountRepository;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	IAccountRepository accountRepository;
	
	@Override
	public Transaction transferMoney(long senderAccounId, long receiverAccountId, double amount, String username,
			String password) throws LowBalanceException, InvalidAccountException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction withdraw(long accounId, String username, String password) throws LowBalanceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction deposit(long accounId, double amount) throws InvalidAccountException, InvalidAmountException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SavingsAccount addSavingsAccount(SavingsAccount saving) throws InvalidDetailsException {
		SavingsAccount savedAccount = accountRepository.save(saving);
		return savedAccount;
	}

	@Override
	public CurrentAccount addCurrentAccount(CurrentAccount current) throws InvalidDetailsException {
		CurrentAccount savedAccount = accountRepository.save(current);
		return savedAccount;
	}
	
    @Override
    @Transactional
    public SavingsAccount updateSavingsAccount(long accountId, SavingAccountRequestSubmitDTO savingRequestDTO) throws InvalidDetailsException {
        Account existingAccount = accountRepository.findById(accountId)
                .orElseThrow(() -> new InvalidDetailsException("Account not found",""));

        if (existingAccount instanceof SavingsAccount) {
            SavingsAccount savingsAccount = (SavingsAccount) existingAccount;
            
            // Update the SavingsAccount entity with the values from the DTO
            savingsAccount.setInterestRate(savingRequestDTO.getInterestRate());
            savingsAccount.setBalance(savingRequestDTO.getBalance());
            savingsAccount.setDateOfOpening(savingRequestDTO.getDateOfOpening());
            savingsAccount.setSavingMinBalance(savingRequestDTO.getSavingMinBalance());
            savingsAccount.setSavingFine(savingRequestDTO.getSavingFine());

            // Save the updated SavingsAccount entity
            SavingsAccount updatedAccount = accountRepository.save(savingsAccount);

            return updatedAccount;
        } else {
            throw new InvalidDetailsException("Account is not a Savings Account","");
        }
    }
	
	@Override
	@Transactional
	public CurrentAccount updateCurrentAccount(long accountId,CurrentAccountRequestSubmitDTO currentRequestDTO) throws InvalidDetailsException {
		Account existingAccount = accountRepository.findById(accountId)
                .orElseThrow(() -> new InvalidDetailsException("Account not found",""));

        if (existingAccount instanceof CurrentAccount) {
            CurrentAccount currentAccount = (CurrentAccount) existingAccount;
            
            // Update the SavingsAccount entity with the values from the DTO
            currentAccount.setInterestRate(currentRequestDTO.getInterestRate());
            currentAccount.setBalance(currentRequestDTO.getBalance());
            currentAccount.setDateOfOpening(currentRequestDTO.getDateOfOpening());
            currentAccount.setCurrentMinBalance(currentRequestDTO.getCurrentMinBalance());
            currentAccount.setCurrentFine(currentRequestDTO.getCurrentFine());

            // Save the updated SavingsAccount entity
            CurrentAccount updatedAccount = accountRepository.save(currentAccount);

            return updatedAccount;
        } else {
            throw new InvalidDetailsException("Account is not a Savings Account","");
        }
	}

	@Override
	public boolean closeSavingsAccount(SavingsAccount accountNo) throws InvalidAccountException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean closeCurrentAccount(CurrentAccount accountNo) throws InvalidAccountException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account findAccountById(int account_id) throws InvalidAccountException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Account> viewAccounts(long customerId) throws DetailsNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SavingsAccount viewSavingAcc(long customerId) throws DetailsNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CurrentAccount viewCurrentAcc(long customerId) throws DetailsNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
