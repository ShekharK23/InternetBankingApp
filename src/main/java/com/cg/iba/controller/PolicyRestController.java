package com.cg.iba.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.iba.dto.PolicyResponseDTO;
import com.cg.iba.entity.Policy;
import com.cg.iba.exception.DetailsNotFoundException;
import com.cg.iba.exception.EmptyListException;
import com.cg.iba.exception.InvalidDetailsException;
import com.cg.iba.service.IPolicyService;
import com.cg.iba.util.PolicyResponseDTOConverter;

@RestController
@RequestMapping("/policy")
public class PolicyRestController {

	@Autowired
	IPolicyService policyService;
	
	@Autowired
	PolicyResponseDTOConverter policyResponseDTOConverter;
	
	@PostMapping("/policy/save") // working
	public Policy savePolicy(@RequestBody PolicyResponseDTO dto) throws InvalidDetailsException  {
		Policy savedPolicy = policyResponseDTOConverter.setPolicyUsingDTO(dto);
		Policy policy = policyService.savePolicy(savedPolicy);
		return policy;
	}
	
	@GetMapping("/policy/fetch/num") // working
	public ResponseEntity<PolicyResponseDTO> getPolicyByPolicyNumber(@RequestParam long policyNumber) throws DetailsNotFoundException{
		Policy savedPolicy = policyService.getPolicyByPolicyNumber(policyNumber);
		PolicyResponseDTO dto = policyResponseDTOConverter.getPolicyUsingDTO(savedPolicy);
		return new ResponseEntity<PolicyResponseDTO>(dto, HttpStatus.OK);
	}

	@GetMapping("/policy/allpolicies") // working
	public ResponseEntity<List<PolicyResponseDTO>> getAllAccounts() throws EmptyListException{
		List<Policy> tempList = policyService.getAllPolicies();
		List<PolicyResponseDTO> temp = new ArrayList<PolicyResponseDTO>();
		for (Policy policy : tempList) {
			temp.add(policyResponseDTOConverter.getPolicyUsingDTO(policy));	
		}
		return new ResponseEntity<List<PolicyResponseDTO>>(temp, HttpStatus.OK);
	}

	@PutMapping("/policy/update") // working
	public ResponseEntity<PolicyResponseDTO> updatePremiumAmountOfPolicyByPolicyNumber(@RequestParam long policyNumber, @RequestParam int newPremiumAmount) throws DetailsNotFoundException {
		Policy savedPolicy = policyService.updatePremiumAmountOfPolicyByPolicyNumber(policyNumber, newPremiumAmount);
		PolicyResponseDTO dto = policyResponseDTOConverter.getPolicyUsingDTO(savedPolicy);
		PolicyResponseDTO policy = new PolicyResponseDTO();

		if (savedPolicy != null) {
			return new ResponseEntity<PolicyResponseDTO>(dto, HttpStatus.OK);
		} else
			return new ResponseEntity<PolicyResponseDTO>(policy, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/policy/check-expiry") // working
	public ResponseEntity<String> checkExpiryDate(@RequestParam long policyNumber) throws DetailsNotFoundException {
		String msg = policyService.checkExpiryDate(policyNumber);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}

	@DeleteMapping("/policy/delete/{policyNumber}") // working
	public ResponseEntity<String> deletePolicy(@PathVariable long policyNumber) throws DetailsNotFoundException{
		policyService.deletePolicy(policyNumber);
		return new ResponseEntity<String>("Policy Deleted", HttpStatus.OK);
	}
}
