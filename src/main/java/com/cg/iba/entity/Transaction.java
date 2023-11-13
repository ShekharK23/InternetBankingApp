package com.cg.iba.entity;

import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import com.cg.iba.util.TransactionDateConverter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long transactionId;
    private double amount;
    
	@Enumerated(EnumType.STRING)
	@Column(name="transactionType")
    private TransactionType transactionType; 
    private String date=TransactionDateConverter.convertLocalDateToString(LocalDate.now());
    
    
	@Enumerated(EnumType.STRING)
	@Column(name="transactionStatus")
    private TransactionStatus transactionStatus;
	
    private String transactionRemarks;
   
}
