package com.cg.iba.entity;

import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.cg.iba.entity.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name="role")
	private Role role;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "accountNum")
	private Account account;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "AdminNum")
	private Admin admin;
}
