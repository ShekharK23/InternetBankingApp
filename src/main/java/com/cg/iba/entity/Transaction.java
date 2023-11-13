package com.cg.iba.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.cg.iba.entity.enums.TransactionStatus;
import com.cg.iba.entity.enums.TransactionType;

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
    private LocalDateTime dateTime;
    
	@Enumerated(EnumType.STRING)
	@Column(name="transactionStatus")
    private TransactionStatus transactionStatus;
	
    private String transactionRemarks;
   
}
