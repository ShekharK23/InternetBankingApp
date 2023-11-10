package com.cg.iba.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Policy {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long policyNumber;
	private String policyName;
	private int policyPremiumAmount;
	private int policySumAssured;
	private String policyExpiryDate;
	
}
