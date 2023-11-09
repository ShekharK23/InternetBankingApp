package com.cg.iba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.cg.iba.entity.Account;
import com.cg.iba.entity.Investment;


@Repository
public interface IInvestmentRepository extends JpaRepository<Investment, Long> {

	//public void deleteByAccount(Account account);
}
