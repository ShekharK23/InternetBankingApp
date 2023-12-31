package com.cg.iba.entity;

import java.time.LocalDate;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
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
	
	private String accountHolderName;
    private String phoneNo;
    private String emailId;
    private int age;
    
	@Enumerated(EnumType.STRING)
	@Column(name="gender")
    private Gender gender; 
	
    private double interestRate;
    private double balance;
    
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate  dateOfOpening;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "dependedId")
    private List<Nominee> nominees;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "policyNumber1")
    private List<Policy> policies;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fdNumber1")
    private List<Investment> investments;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "beneficiaryID1")
    private List<Beneficiary> beneficiaries;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "transactionAccountID1")
    private List<Transaction> transactions;
    
    @OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "debitCardNumber1")
	private DebitCard debitCard;

 }
