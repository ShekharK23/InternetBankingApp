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
public class TermAccount extends Account{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private double amount;
    private int months; 
    private double penaltyAmount; 

}