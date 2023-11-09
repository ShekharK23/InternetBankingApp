package com.cg.iba.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
@AllArgsConstructor
@NoArgsConstructor
public class Branch {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long branchIFSC;
	private String branchCity;
	private String branchArea;
	
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "accountNumberBr")
//	private List<Account> allAccounts;
}
