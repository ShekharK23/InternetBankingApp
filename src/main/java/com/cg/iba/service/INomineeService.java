package com.cg.iba.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.cg.iba.entity.Nominee;
import com.cg.iba.exception.DetailsNotFoundException;
import com.cg.iba.exception.EmptyListException;
import com.cg.iba.exception.InvalidAccountException;
import com.cg.iba.exception.InvalidDetailsException;

@Service
public interface INomineeService {

	public Nominee addNominee(Nominee nominee) throws InvalidDetailsException;
	public Nominee updateNominee(Nominee nominee) throws InvalidDetailsException;
	public boolean deleteNominee(long nomineeId) throws DetailsNotFoundException ;
	public Nominee findNomineeById(long nomineeId)throws DetailsNotFoundException ;
	public Set<Nominee> listAllNominees(long accountid) throws InvalidAccountException, EmptyListException ;
}
