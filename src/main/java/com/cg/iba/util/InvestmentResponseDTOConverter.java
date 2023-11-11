package com.cg.iba.util;

import org.springframework.stereotype.Component;

import com.cg.iba.dto.InvestmentResponseDTO;
import com.cg.iba.entity.Investment;

@Component
public class InvestmentResponseDTOConverter {
	public Investment setInvestmentUsingDTO(InvestmentResponseDTO dto) {
		Investment investment = new Investment();
		
		investment.setFdAmount(dto.getFdAmount());
		investment.setFdInterestRate(dto.getFdInterestRate());
		investment.setFdTermMonths(dto.getFdTermMonths());
		investment.setFdStartDate(dto.getFdStartDate());
		investment.setFdMaturityDate(dto.getFdMaturityDate());
		
		return investment;
	}
	
	public InvestmentResponseDTO getInvestmentUsingDTO(Investment investment) {
		InvestmentResponseDTO dto = new InvestmentResponseDTO();
		
		dto.setFdNumber(investment.getFdNumber());
		dto.setFdAmount(investment.getFdAmount());
		dto.setFdInterestRate(investment.getFdInterestRate());
		dto.setFdTermMonths(investment.getFdTermMonths());
		dto.setFdStartDate(investment.getFdStartDate());
		dto.setFdMaturityDate(investment.getFdMaturityDate());
		
		return dto;
	}
}
