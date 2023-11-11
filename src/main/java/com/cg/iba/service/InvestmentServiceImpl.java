package com.cg.iba.service;

import java.time.LocalDate;	

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.iba.entity.Investment;
import com.cg.iba.exception.DetailsNotFoundException;
import com.cg.iba.exception.EmptyListException;
import com.cg.iba.exception.InvalidDetailsException;
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
	public Investment addInvestment(Investment investment) throws InvalidDetailsException{
		Investment savedInvestment = investmentRepository.save(investment);
		if(savedInvestment!=null) return savedInvestment;
		throw new InvalidDetailsException("Details of Policy provided are not valid", InvestmentServiceImpl.class+"");
	}

	@Override
	public Investment getInvestmentByFdNumber(long fdNumber) throws DetailsNotFoundException{
		Investment savedInvestment = investmentRepository.findById(fdNumber)
				.orElseThrow(()-> new DetailsNotFoundException("You have entered wrong fdNumber "+fdNumber, InvestmentServiceImpl.class+""));
		return savedInvestment;
	}

	@Override
	public List<Investment> getAllInvestments() throws EmptyListException{
		List<Investment> savedAllInvestments = investmentRepository.findAll();
		if(savedAllInvestments.isEmpty()) {
			throw new EmptyListException("No Investments Found", InvestmentServiceImpl.class+"");
		}
		return savedAllInvestments;
	}

	@Override
	@Transactional
	public Investment updateTermMonthOfInvestmentByFdNumber(long fdNumber, int newFdTermMonths) throws DetailsNotFoundException{
		Investment savedInvestment = getInvestmentByFdNumber(fdNumber);
		if(savedInvestment!=null) {
			savedInvestment.setFdTermMonths(newFdTermMonths);
			return savedInvestment;
		}
		return null;
	}

	@Override
	@Transactional
	public String checkMaturityDate(long fdNumber) throws DetailsNotFoundException{
		try {
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
		} catch (DetailsNotFoundException e) {
			throw new DetailsNotFoundException("Investment not Found with FD Number "+fdNumber , InvestmentServiceImpl.class+"");
		}
	}

	@Override
	public void deleteInvestment(long fdNumber) throws DetailsNotFoundException{
		Investment investment = investmentRepository.findById(fdNumber)
				.orElseThrow(() -> new DetailsNotFoundException("Investment not Found with FD Number "+fdNumber , InvestmentServiceImpl.class+""));
		investmentRepository.delete(investment);
	}

}
