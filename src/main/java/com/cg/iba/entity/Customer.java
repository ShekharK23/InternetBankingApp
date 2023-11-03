package com.cg.iba.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long customerId; 
    private String customerName;
    private String phoneNo;
    private String emailId;
    private int age;
    
	@Enumerated(EnumType.STRING)
	@Column(name="gender")
    private Gender gender; 
   
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "AccountId1")
    private List<Account> accounts;
}
