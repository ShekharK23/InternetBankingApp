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
public class Nominee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long nomineeId;
    private String name;
    private String govtId; 
    private String govtIdType; 
    private String phoneNo;
    private Relation relation; 
 }
