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

import com.cg.iba.dto.NomineeRequestSubmitDTO;
import com.cg.iba.dto.NomineeResponseDTO;
import com.cg.iba.entity.Nominee;
import com.cg.iba.entity.Transaction;
import com.cg.iba.exception.DetailsNotFoundException;
import com.cg.iba.exception.EmptyListException;
import com.cg.iba.exception.InvalidAccountException;
import com.cg.iba.exception.InvalidAmountException;
import com.cg.iba.exception.InvalidDetailsException;
import com.cg.iba.service.INomineeService;
import com.cg.iba.util.NomineeDTOMapper;

@RestController
@RequestMapping("/nominee")
public class NomineeRestController {
	@Autowired
	INomineeService nomineeService;

	@Autowired
	NomineeDTOMapper nomineeDTOMapper;

	@PostMapping("/addNominee") // working
	public ResponseEntity<NomineeResponseDTO> addNominee(@RequestBody NomineeRequestSubmitDTO nominee)
			throws InvalidDetailsException {
		Nominee n = nomineeDTOMapper.setNomineeUsingDTO(nominee);
		Nominee savedNominee = nomineeService.addNominee(n);
		NomineeResponseDTO dto = nomineeDTOMapper.getNomineeUsingDTO(savedNominee);
		return new ResponseEntity<NomineeResponseDTO>(dto, HttpStatus.OK);
	}

	@GetMapping("/findNomineeById") // Working
	public ResponseEntity<NomineeResponseDTO> findNomineeById(@RequestParam long nomineeId)
			throws DetailsNotFoundException {

		Nominee savedNominee = nomineeService.findNomineeById(nomineeId);
		NomineeResponseDTO dto = nomineeDTOMapper.getNomineeUsingDTO(savedNominee);
		return new ResponseEntity<NomineeResponseDTO>(dto, HttpStatus.FOUND);
	}

	@GetMapping("/listAllNominee") // working
	public ResponseEntity<List<NomineeResponseDTO>> listAllNominees(@RequestParam long accountId)
			throws InvalidAccountException, EmptyListException {
		List<Nominee> allNominee = nomineeService.listAllNominees(accountId);
		List<NomineeResponseDTO> mapped = new ArrayList<NomineeResponseDTO>();
		for (Nominee nominee : allNominee) {
			mapped.add(nomineeDTOMapper.getNomineeUsingDTO(nominee));
		}
		return new ResponseEntity<List<NomineeResponseDTO>>(mapped, HttpStatus.FOUND);
	}

	@PutMapping("/updateNomineeDetails") // working
	public ResponseEntity<NomineeResponseDTO> updateNominee(@RequestParam long id, @RequestBody NomineeRequestSubmitDTO nominee)
			throws InvalidDetailsException, DetailsNotFoundException {
		Nominee updatedNominee = nomineeService.updateNominee(id, nominee);
		NomineeResponseDTO n=nomineeDTOMapper.getNomineeUsingDTO(updatedNominee);
		
		return new ResponseEntity<NomineeResponseDTO>(n, HttpStatus.FOUND);
	}

	@DeleteMapping("/deleteNomineeById/{nomineeId}")
	public ResponseEntity<String> deleteNomineeById(@PathVariable long nomineeId) throws DetailsNotFoundException {

		nomineeService.deleteNominee(nomineeId);
		return new ResponseEntity<String>("The Nominee is deleted for the id-:" + nomineeId, HttpStatus.ACCEPTED);

	}
	
	
	
//	@PutMapping("/deposit")
//	public Transaction deposit(@RequestParam long accounId,@RequestParam double amount,@RequestBody Transaction t) throws InvalidAccountException, InvalidAmountException {
//		Transaction saved=accountService.deposit(accounId, amount, t);
//		return saved;
//	}
}
