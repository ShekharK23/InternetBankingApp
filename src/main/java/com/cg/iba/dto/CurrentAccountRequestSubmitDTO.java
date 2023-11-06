package com.cg.iba.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentAccountRequestSubmitDTO {

	private double interestRate;
    private double balance;
    private LocalDate  dateOfOpening;
    
    private double currentMinBalance;
    private double currentFine;
}