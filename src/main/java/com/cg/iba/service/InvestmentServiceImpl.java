package com.cg.iba.service;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.iba.entity.Account;
import com.cg.iba.entity.Investment;
import com.cg.iba.repository.IAccountRepository;
import com.cg.iba.repository.IInvestmentRepository;
import com.cg.iba.util.InvestmentDateConverter;


@Service
public class InvestmentServiceImpl implements IInvestmentService {
	
	@Autowired
	IInvestmentRepository investmentRepository;
	
	@Autowired
	IAccountRepository accountRepository;

	@Override
	public String addInvestment(Investment investment) {
		Investment savedInvestment = investmentRepository.save(investment);
		if(savedInvestment!=null) return "Investment Added "+savedInvestment;
		return "Investment not Addded";
	}

	@Override
	public Investment getInvestmentByFdNumber(long fdNumber) {
		Investment savedInvestment = investmentRepository.findById(fdNumber).get();
		return savedInvestment;
	}

	@Override
	public List<Investment> getAllInvestments() {
		List<Investment> savedAllInvestments = investmentRepository.findAll();
		return savedAllInvestments;
	}

	@Override
	@Transactional
	public Investment updateTermMonthOfInvestmentByFdNumber(long fdNumber, int newFdTermMonths) {
		Investment savedInvestment = getInvestmentByFdNumber(fdNumber);
		if(savedInvestment!=null) {
			savedInvestment.setFdTermMonths(newFdTermMonths);
			return savedInvestment;
		}
		return null;
	}

	@Override
	@Transactional
	public String checkMaturityDate(long fdNumber) {
		Investment savedInvestment = getInvestmentByFdNumber(fdNumber);
		if(savedInvestment!=null) {
			LocalDate currentDate = LocalDate.now();
			LocalDate fdMaturityDate = InvestmentDateConverter.getDateFromString(savedInvestment.getFdMaturityDate());
			
			if(currentDate.isAfter(fdMaturityDate)) {
				return "FD is Matured\n"+"Maturity Date is : "+fdMaturityDate;
			}
			else return "FD is Not Matured\n"+"Maturity Date is : "+fdMaturityDate;
		}
		return null;
	}

	@Override
	public void deleteInvestmentForAccount(long accountNumber) {
		Optional<Account> account = accountRepository.findById(accountNumber);
        if (account.isPresent()) {
            Account acc = account.get();
            investmentRepository.deleteByAccount(acc);
        }
	}

}
