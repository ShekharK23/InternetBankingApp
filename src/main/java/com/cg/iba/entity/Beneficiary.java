package com.cg.iba.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Beneficiary {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long  beneficiaryId;
	private String beneficiaryName;
	private long  beneficiaryAccNo;
	private String IFSC;
	private AccountType accountType;
}
