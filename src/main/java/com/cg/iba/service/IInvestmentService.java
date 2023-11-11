package com.cg.iba.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.iba.entity.Investment;
import com.cg.iba.exception.DetailsNotFoundException;
import com.cg.iba.exception.EmptyListException;
import com.cg.iba.exception.InvalidDetailsException;

@Service
public interface IInvestmentService {
	public Investment addInvestment(Investment investment) throws InvalidDetailsException;
	public Investment getInvestmentByFdNumber(long fdNumber) throws DetailsNotFoundException;
	public List<Investment> getAllInvestments() throws EmptyListException;
	public Investment updateTermMonthOfInvestmentByFdNumber(long fdNumber,int newFdTermMonths) throws DetailsNotFoundException;
	public String checkMaturityDate(long fdNumber) throws DetailsNotFoundException;
	public void deleteInvestment(long fdNumber)throws DetailsNotFoundException;
}
