package com.cg.iba.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.iba.dto.CurrentAccountRequestSubmitDTO;
import com.cg.iba.dto.SavingAccountRequestSubmitDTO;
import com.cg.iba.entity.Account;
import com.cg.iba.entity.CurrentAccount;
import com.cg.iba.entity.DebitCard;
import com.cg.iba.entity.Investment;
import com.cg.iba.entity.Nominee;
import com.cg.iba.entity.Policy;
import com.cg.iba.entity.SavingsAccount;
import com.cg.iba.entity.Transaction;
import com.cg.iba.exception.DetailsNotFoundException;
import com.cg.iba.exception.InvalidAccountException;
import com.cg.iba.exception.InvalidAmountException;
import com.cg.iba.exception.InvalidDetailsException;
import com.cg.iba.exception.LowBalanceException;
import com.cg.iba.repository.IAccountRepository;
import com.cg.iba.repository.IDebitCardRepository;
import com.cg.iba.repository.IInvestmentRepository;
import com.cg.iba.repository.INomineeRepository;
import com.cg.iba.repository.IPolicyRepository;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	IAccountRepository accountRepository;

	@Autowired
	IDebitCardRepository debitCardRepository;

	@Autowired
	INomineeRepository nomineeRepository;

	@Autowired
	INomineeService nomineeService;

	@Autowired
	IPolicyRepository policyRepository;

	@Autowired
	IInvestmentRepository investmentRepository;

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
	public SavingsAccount updateSavingsAccount(long accountId, SavingAccountRequestSubmitDTO savingRequestDTO)
			throws InvalidDetailsException {
		Account existingAccount = accountRepository.findById(accountId)
				.orElseThrow(() -> new InvalidDetailsException("Account not found", ""));

		if (existingAccount instanceof SavingsAccount) {
			SavingsAccount savingsAccount = (SavingsAccount) existingAccount;

			savingsAccount.setAccountHolderName(savingRequestDTO.getAccountHolderName());
			savingsAccount.setPhoneNo(savingRequestDTO.getPhoneNo());
			savingsAccount.setEmailId(savingRequestDTO.getEmailId());
			savingsAccount.setAge(savingRequestDTO.getAge());
			savingsAccount.setGender(savingRequestDTO.getGender());
			savingsAccount.setInterestRate(savingRequestDTO.getInterestRate());
			savingsAccount.setBalance(savingRequestDTO.getBalance());
			savingsAccount.setDateOfOpening(savingRequestDTO.getDateOfOpening());
			savingsAccount.setSavingMinBalance(savingRequestDTO.getSavingMinBalance());
			savingsAccount.setSavingFine(savingRequestDTO.getSavingFine());

			SavingsAccount updatedAccount = accountRepository.save(savingsAccount);

			return updatedAccount;
		} else {
			throw new InvalidDetailsException("Account is not a Savings Account", "");
		}
	}

	@Override
	@Transactional
	public CurrentAccount updateCurrentAccount(long accountId, CurrentAccountRequestSubmitDTO currentRequestDTO)
			throws InvalidDetailsException {
		Account existingAccount = accountRepository.findById(accountId)
				.orElseThrow(() -> new InvalidDetailsException("Account not found", ""));

		if (existingAccount instanceof CurrentAccount) {
			CurrentAccount currentAccount = (CurrentAccount) existingAccount;

			currentAccount.setAccountHolderName(currentRequestDTO.getAccountHolderName());
			currentAccount.setPhoneNo(currentRequestDTO.getPhoneNo());
			currentAccount.setEmailId(currentRequestDTO.getEmailId());
			currentAccount.setAge(currentRequestDTO.getAge());
			currentAccount.setGender(currentRequestDTO.getGender());
			currentAccount.setInterestRate(currentRequestDTO.getInterestRate());
			currentAccount.setBalance(currentRequestDTO.getBalance());
			currentAccount.setDateOfOpening(currentRequestDTO.getDateOfOpening());
			currentAccount.setCurrentMinBalance(currentRequestDTO.getCurrentMinBalance());
			currentAccount.setCurrentFine(currentRequestDTO.getCurrentFine());

			CurrentAccount updatedAccount = accountRepository.save(currentAccount);

			return updatedAccount;
		} else {
			throw new InvalidDetailsException("Account is not a Savings Account", "");
		}
	}

	@Override
	public boolean closeSavingsAccount(SavingsAccount accountNo) throws InvalidAccountException {
		Account existingAccount = accountRepository.findById(accountNo.getAccountId()).get();

		if (existingAccount == null) {
			new InvalidAccountException("Account not found", "");
			return false;
		} else {
			accountRepository.deleteById(accountNo.getAccountId());
			return true;
		}

	}

	@Override
	public boolean closeCurrentAccount(CurrentAccount accountNo) throws InvalidAccountException {
		Account existingAccount = accountRepository.findById(accountNo.getAccountId()).get();

		if (existingAccount == null) {
			new InvalidAccountException("Account not found", "");
			return false;
		} else {
			accountRepository.deleteById(accountNo.getAccountId());
			return true;
		}

	}

	@Override
	public Account findAccountById(long account_id) throws InvalidAccountException {
		Optional<Account> accountOptional = accountRepository.findById(account_id);

		if (accountOptional.isPresent()) {
			return accountOptional.get();
		} else {
			throw new InvalidAccountException("Account Not Found.", "");
		}
	}

	@Override
	public List<Account> viewAccounts() {
		List<Account> accounts = accountRepository.findAll();
		return accounts;
	}

	@Override
	public SavingsAccount viewSavingAcc(long accountId) throws DetailsNotFoundException {
		Account account = accountRepository.findById(accountId).get();
		if (account instanceof SavingsAccount) {
			return (SavingsAccount) account;
		}
		return null;
	}

	@Override
	public CurrentAccount viewCurrentAcc(long accountId) throws DetailsNotFoundException {
		Account account = accountRepository.findById(accountId).get();
		if (account instanceof CurrentAccount) {
			return (CurrentAccount) account;
		}
		return null;
	}

	@Override
	@Transactional
	public Account addDebitCardToAccount(long accNum, long debitCardNum) throws InvalidAccountException {
		Account savedAcc = findAccountById(accNum);
		DebitCard card = debitCardRepository.findById(debitCardNum).get();
		if (savedAcc != null && card != null) {
			savedAcc.setDebitCard(card);
			return savedAcc;
		}
		return null;
	}

	@Override
	@Transactional
	public Account addNomineeToAccount(long nomineeId, long accNum)
			throws InvalidAccountException, DetailsNotFoundException, InvalidDetailsException {
		Account savedAcc = findAccountById(accNum);
		Nominee nominee = nomineeService.findNomineeById(nomineeId);
		System.out.println(savedAcc.getAccountId() + " - " + savedAcc.getBalance());
		System.out.println(nominee.getName());
		if (savedAcc != null && nominee != null) {
			System.out.println("inside if ");
			List<Nominee> allNominees = savedAcc.getNominees();
			System.out.println("===>> 1 " + allNominees.size());

			allNominees.add(nominee);

			savedAcc.setNominees(allNominees);
			System.out.println(savedAcc);

			if (savedAcc instanceof SavingsAccount) {
				SavingsAccount sa = (SavingsAccount) savedAcc;
				addSavingsAccount(sa);
				return sa;
			}
			if (savedAcc instanceof CurrentAccount) {
				CurrentAccount ca = (CurrentAccount) savedAcc;
				addCurrentAccount(ca);
				return ca;
			}

		}
		return null;
	}

	@Override
	@Transactional
	public Account addPolicyToAccount(long policyId, long accNum)
			throws InvalidAccountException, InvalidDetailsException {
		Account savedAcc = findAccountById(accNum);
		Policy policy = policyRepository.findById(policyId).get();

		if (savedAcc != null && policy != null) {
			List<Policy> allPolicy = savedAcc.getPolicies();
			allPolicy.add(policy);
			savedAcc.setPolicies(allPolicy);

			if (savedAcc instanceof SavingsAccount) {
				SavingsAccount sa = (SavingsAccount) savedAcc;
				addSavingsAccount(sa);
				return sa;
			}

			if (savedAcc instanceof CurrentAccount) {
				CurrentAccount ca = (CurrentAccount) savedAcc;
				addCurrentAccount(ca);
				return ca;
			}
		}
		return null;
	}

	@Override
	public List<Policy> getPolicyByAccountId(long accNum) {
		Account account = accountRepository.findById(accNum).get();

		if (account != null) {
			return account.getPolicies();
		}
		return Collections.emptyList();
	}

	@Override
	@Transactional
	public Account addInvestmentToAccount(long fdNumber, long accNum)
			throws InvalidAccountException, InvalidDetailsException {
		Account savedAcc = findAccountById(accNum);
		Investment investment = investmentRepository.findById(fdNumber).get();

		if (savedAcc != null & investment != null) {
			List<Investment> allInvestments = savedAcc.getInvestments();
			allInvestments.add(investment);
			savedAcc.setInvestments(allInvestments);

			if (savedAcc instanceof SavingsAccount) {
				SavingsAccount sa = (SavingsAccount) savedAcc;
				addSavingsAccount(sa);
				return sa;
			}

			if (savedAcc instanceof CurrentAccount) {
				CurrentAccount ca = (CurrentAccount) savedAcc;
				addCurrentAccount(ca);
				return ca;
			}
		}

		return null;
	}

	@Override
	public List<Investment> getInvestmentByAccountId(long accNum) {
		Account account = accountRepository.findById(accNum).get();

		if (account != null) {
			return account.getInvestments();
		}
		return Collections.emptyList();
	}
}
