package com.cg.iba.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.cg.iba.entity.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountAddDTO {

    private double interestRate;
    private double balance;
    private LocalDate  dateOfOpening;
    private String customerName;
    private String phoneNo;
    private String emailId;
    private int age;
    private Gender gender; 
    
}