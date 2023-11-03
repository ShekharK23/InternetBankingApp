package com.cg.iba.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@ApiModel(description = "Details about Account Bean")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "Unique Account Number of Account")
    private long accountId; 
    private double interestRate;
    private double balance;
    private LocalDate  dateOfOpening;
    private Set<Customer> customers;
    private Set<Nominee> nominees;
    private Set<Beneficiary> beneficiaries;
    
 }
