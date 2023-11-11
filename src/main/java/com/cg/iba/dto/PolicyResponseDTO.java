package com.cg.iba.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyResponseDTO {
	private long policyNumber;
	private String policyName;
	private int policyPremiumAmount;
	private int policySumAssured;
	private String policyExpiryDate;
}
