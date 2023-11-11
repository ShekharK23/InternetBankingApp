package com.cg.iba.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentResponseDTO {
	private long fdNumber;
	private int fdAmount;
	private float fdInterestRate;
	private int fdTermMonths;
	private String fdStartDate;
	private String fdMaturityDate;
}
