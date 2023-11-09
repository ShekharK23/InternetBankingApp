package com.cg.iba.entity;

import java.time.LocalDate;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="accounts_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@ApiModel(description = "Details about Account Bean")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "Unique Account Number of Account")
    private long accountId; 
    private double interestRate;
    private double balance;
    
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate  dateOfOpening;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId1")
    private Customer customer;
    
    @OneToMany(cascade = CascadeType.ALL)//(mappedBy = "account" ,cascade = CascadeType.ALL)
    @JoinColumn( name = "dependedId")
    private List<Nominee> nominees;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "beneficiaryID1")
    private List<Beneficiary> beneficiaries;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "transactionID1")
    private List<Transaction> transactions;
    
    @OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "debitCardNumber1")
	private DebitCard debitCard;
   /* 
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "branchIFSC1")
	private Branch branch;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "policyNumber1")
	private List<Policy> allPolicy;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fdNumber1")
	private List<Investment> allInvestment;
    */
 }
