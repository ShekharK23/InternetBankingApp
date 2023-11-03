package com.cg.iba.service;

import java.util.List;	

import org.springframework.stereotype.Service;

import com.cg.iba.entity.Policy;


@Service
public interface IPolicyService {
	public long savePolicy(Policy policy);
	public Policy getPolicyByPolicyNumber(long policyNumber);
	public List<Policy> getAllPolicies();
	public Policy updatePremiumAmountOfPolicyByPolicyNumber(long policyNumber, int newPremiumAmount);
	public String checkExpiryDate(long policyNumber);
	public void deletePolicyForAccount(long accountNumber);
}
