package com.cg.iba.entity;

import java.time.LocalDateTime;

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
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long transactionId;
    private double amount;
    private TransactionType transactionType; 
    private LocalDateTime dateTime; 
    private Account bankAccount; 
    private TransactionStatus transactionStatus;
    private String transactionRemarks;
    
}
