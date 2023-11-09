package com.cg.iba.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cg.iba.entity.Beneficiary;
import com.cg.iba.entity.Branch;
import com.cg.iba.entity.Customer;
import com.cg.iba.entity.DebitCard;
import com.cg.iba.entity.Investment;
import com.cg.iba.entity.Nominee;
import com.cg.iba.entity.Policy;
import com.cg.iba.entity.Transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

	private long accountId; 
	private double interestRate;
	private double balance;
	private LocalDate  dateOfOpening;   
	private Customer customer;
	private List<Nominee> nominees;
	private List<Beneficiary> beneficiaries;
	private List<Transaction> transactions;
	private DebitCard debitCard;
	private Branch branch;
	private List<Policy> allPolicy;
	private List<Investment> allInvestment;
	
}
