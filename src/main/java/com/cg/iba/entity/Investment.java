package com.cg.iba.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Investment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long fdNumber;
	private int fdAmount;
	private float fdInterestRate;
	private int fdTermMonths;
	private String fdStartDate;
	private String fdMaturityDate;
	
	/*
	 * @ManyToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "accountNumber") private Account account;
	 */
}
