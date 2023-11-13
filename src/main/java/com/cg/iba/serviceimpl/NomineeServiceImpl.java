package com.cg.iba.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.iba.entity.Account;
import com.cg.iba.entity.Nominee;
import com.cg.iba.exception.DetailsNotFoundException;
import com.cg.iba.exception.EmptyListException;
import com.cg.iba.exception.InvalidAccountException;
import com.cg.iba.exception.InvalidDetailsException;
import com.cg.iba.repository.IAccountRepository;
import com.cg.iba.repository.INomineeRepository;
import com.cg.iba.service.INomineeService;

@Service
public class NomineeServiceImpl implements INomineeService {

	@Autowired
	INomineeRepository nomineeRepository;
	
	@Autowired
	IAccountRepository accountRepository;
	
	@Override
	public Nominee addNominee(Nominee nominee) throws InvalidDetailsException {
		Nominee savedNominee = nomineeRepository.save(nominee);
		if (savedNominee != null) {
			return savedNominee;
		}
		return null;
	}

	@Override
	public Nominee updateNominee(Nominee nominee) throws InvalidDetailsException, DetailsNotFoundException {
		long id = nominee.getNomineeId();
		if(id !=0) {
			Nominee existingNominee = findNomineeById(id);
			if(nominee.getName()!=null) {
				existingNominee.setName(nominee.getName());
			}
			if(nominee.getGovtId()!=null) {
				existingNominee.setGovtId(nominee.getGovtId());
			}
			if(nominee.getGovtIdType()!=null) {
				existingNominee.setGovtIdType(nominee.getGovtIdType());
			}
			if(nominee.getPhoneNo()!=null) {
				existingNominee.setPhoneNo(nominee.getPhoneNo());
			}
			return nomineeRepository.save(existingNominee);
		}
		return null;
	}

	@Override
	public boolean deleteNominee(long nomineeId) throws DetailsNotFoundException {
		if(nomineeId!=0) {
			nomineeRepository.deleteById(nomineeId);
			return true;
		}
		return false;
	}

	@Override
	public Nominee findNomineeById(long nomineeId) throws DetailsNotFoundException {
		if (nomineeId != 0) {
			Nominee nominee = nomineeRepository.findById(nomineeId).get();
			return nominee;
		}
		return null;
	}

	@Override
	public List<Nominee> listAllNominees(long accountid) throws InvalidAccountException, EmptyListException {
		Account account = accountRepository.findById(accountid).get();
		List<Nominee> allNominees = account.getNominees();
		
//		if (allNominees != null && allNominees.size() != 0) {
//			return allNominees;
//		}
//		return null;
		
		return allNominees;
	}
	
	
 
}
