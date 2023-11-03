package com.cg.iba.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.iba.entity.Beneficiary;
import com.cg.iba.exception.DetailsNotFoundException;
import com.cg.iba.exception.EmptyListException;
import com.cg.iba.exception.InvalidAccountException;
import com.cg.iba.exception.InvalidDetailsException;

@Repository
public interface IBeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

//	public Beneficiary addBeneficiary(Beneficiary beneficiary)throws InvalidDetailsException;
//	public Beneficiary updateBeneficiary(Beneficiary beneficiary) throws InvalidDetailsException;
//	public boolean deleteBeneficiary(long beneficiaryId) throws DetailsNotFoundException;
//	public Beneficiary findBeneficiaryById(long beneficiaryId) throws DetailsNotFoundException;
//	public Set<Beneficiary> listAllBeneficiaries(long accountid) throws InvalidAccountException,EmptyListException;
}
