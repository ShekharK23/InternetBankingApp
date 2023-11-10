package com.cg.iba.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class Nominee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long nomineeId;
    private String name;
    private String govtId; 
    private String govtIdType; 
    private String phoneNo;
    
	@Enumerated(EnumType.STRING)
	@Column(name="relation")
    private Relation relation; 
   
 }
