package com.cg.iba.entity;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavingsAccount extends Account{

    private double savingMinBalance;
    private double savingFine; 
    
}
