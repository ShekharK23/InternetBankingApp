package com.cg.iba.service;

import java.time.LocalDate;		

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.iba.entity.Account;
import com.cg.iba.entity.Policy;
import com.cg.iba.repository.IAccountRepository;
import com.cg.iba.repository.IPolicyRepository;

@Service
public class PolicyServiceImpl implements IPolicyService {
	
	@Autowired
	IPolicyRepository policyRepository;
	
	@Autowired
	IAccountRepository accountRepository;

	@Override
	public long savePolicy(Policy policy) {
		Policy savedPolicy = policyRepository.save(policy);
		if(savedPolicy != null)
			return savedPolicy.getPolicyNumber();
		return 0;
	}

	@Override
	public Policy getPolicyByPolicyNumber(long policyNumber) {
		Policy savedPolicy = policyRepository.findById(policyNumber).get();
		return savedPolicy;
	}

	@Override
	public List<Policy> getAllPolicies() {
		List<Policy> allPolicies = policyRepository.findAll();
		return allPolicies;
	}

	@Override
	@Transactional
	public Policy updatePremiumAmountOfPolicyByPolicyNumber(long policyNumber, int newPremiumAmount) {
		Policy savedPolicy = getPolicyByPolicyNumber(policyNumber);
		if(savedPolicy != null) {
			savedPolicy.setPolicyPremiumAmount(newPremiumAmount);
			return savedPolicy;
		}
		return null;
	}

	@Override
	public String checkExpiryDate(long policyNumber) {
		Policy savedPolicy = getPolicyByPolicyNumber(policyNumber);
		if(savedPolicy!=null) {
			LocalDate currentDate = LocalDate.now();
			//LocalDate expiryDate = PolicyDateConverter.getDateFromString(savedPolicy.getPolicyExpiryDate());
			
//			if(currentDate.isAfter(expiryDate)) {
//				return "Policy is Expired";
//			}		
		}
		return "Policy is yet to Expired";
	}
	
	@Override
	@Transactional
	public void deletePolicyForAccount(long accountNumber) {
		Optional<Account> account = accountRepository.findById(accountNumber);
        if (account.isPresent()) {
            Account acc = account.get();
            policyRepository.deleteByAccount(acc);
        }
	}

}
