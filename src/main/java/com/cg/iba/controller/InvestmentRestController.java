package com.cg.iba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.iba.dto.InvestmentResponseDTO;
import com.cg.iba.entity.Investment;
import com.cg.iba.exception.DetailsNotFoundException;
import com.cg.iba.exception.EmptyListException;
import com.cg.iba.exception.InvalidDetailsException;
import com.cg.iba.service.IInvestmentService;
import com.cg.iba.util.InvestmentResponseDTOConverter;

@RestController
@RequestMapping("/investment")
public class InvestmentRestController {
	
	@Autowired
	IInvestmentService investmentService;
	
	@Autowired
	InvestmentResponseDTOConverter investmentResponseDTOConverter;
	
	@PostMapping("/investment/save")
	public ResponseEntity<Investment> addInvestment(@RequestBody InvestmentResponseDTO dto) throws InvalidDetailsException{
		Investment savedInvestment = investmentResponseDTOConverter.setInvestmentUsingDTO(dto);
		Investment investment = investmentService.addInvestment(savedInvestment);
		return new ResponseEntity<Investment>(investment,HttpStatus.CREATED);
	}
	
	@GetMapping("/investment/fetch/num")
	public ResponseEntity<InvestmentResponseDTO> getInvestmentByFdNumber(@RequestParam long fdNumber) throws DetailsNotFoundException{
		Investment savedInvestment = investmentService.getInvestmentByFdNumber(fdNumber);
		InvestmentResponseDTO dto = investmentResponseDTOConverter.getInvestmentUsingDTO(savedInvestment);
		return new ResponseEntity<InvestmentResponseDTO>(dto,HttpStatus.OK);
	}
	
	@GetMapping("/investment/allinvestments")
	public ResponseEntity<List<Investment>> getAllInvestments() throws EmptyListException{
		List<Investment> temp = investmentService.getAllInvestments();
		return new ResponseEntity<List<Investment>>(temp,HttpStatus.OK);
	}
	
	@PutMapping("/investment/update")
	public ResponseEntity<InvestmentResponseDTO> updateTermMonthOfInvestmentByFdNumber(@RequestParam long fdNumber, @RequestParam int newFdTermMonths) throws DetailsNotFoundException{
		Investment savedInvestment = investmentService.updateTermMonthOfInvestmentByFdNumber(fdNumber, newFdTermMonths);
		InvestmentResponseDTO dto = investmentResponseDTOConverter.getInvestmentUsingDTO(savedInvestment);
		InvestmentResponseDTO investment = new InvestmentResponseDTO();
		
		if(savedInvestment != null) {
			return new ResponseEntity<InvestmentResponseDTO>(dto,HttpStatus.OK);
		}
		else return new ResponseEntity<InvestmentResponseDTO>(investment,HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/investment/check-maturity")
	public ResponseEntity<String> checkMaturityDate(@RequestParam long fdNumber) throws DetailsNotFoundException{
		String msg = investmentService.checkMaturityDate(fdNumber);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@DeleteMapping("/investment/delete/{fdNumber}")
	public ResponseEntity<String> deleteInvestment(@RequestParam long fdNumber) throws DetailsNotFoundException{
		investmentService.deleteInvestment(fdNumber);
		return new ResponseEntity<String>("Investment Deleted",HttpStatus.OK);
	}
}
