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
@NoArgsConstructor
@AllArgsConstructor
public class DebitCard {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long debitCardNumber;
	private int debitCardPin;
	private String debitCardExpiryDate;
	private int debitCardLimit;
	private String debitCardStatus; //allocated or not allocated
	
}
