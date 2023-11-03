package com.cg.iba.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.iba.entity.Investment;

@Service
public interface IInvestmentService {
	public String addInvestment(Investment investment);
	public Investment getInvestmentByFdNumber(long fdNumber);
	public List<Investment> getAllInvestments();
	public Investment updateTermMonthOfInvestmentByFdNumber(long fdNumber,int newFdTermMonths);
	public String checkMaturityDate(long fdNumber);
	public void deleteInvestmentForAccount(long accountNumber);
}
