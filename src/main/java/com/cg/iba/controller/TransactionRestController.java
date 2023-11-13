package com.cg.iba.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.iba.dto.NomineeRequestSubmitDTO;
import com.cg.iba.dto.NomineeResponseDTO;
import com.cg.iba.dto.TransactionRequestDTO;
import com.cg.iba.dto.TransactionResponseDTO;
import com.cg.iba.entity.Nominee;
import com.cg.iba.entity.Transaction;
import com.cg.iba.exception.DetailsNotFoundException;
import com.cg.iba.exception.EmptyListException;
import com.cg.iba.exception.InvalidAccountException;
import com.cg.iba.exception.InvalidDetailsException;
import com.cg.iba.exception.LowBalanceException;
import com.cg.iba.service.ITransactionService;
import com.cg.iba.util.TransactionDTOMapper;

@RestController
@RequestMapping("/transaction")
public class TransactionRestController {

	@Autowired
	ITransactionService transactionService;

	@Autowired
	TransactionDTOMapper transactionDTOMapper;
	
	@PostMapping("/createTransaction")
	public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody TransactionRequestDTO transaction)
			throws InvalidDetailsException {
		Transaction t = transactionDTOMapper.setTransactionUsingDTO(transaction);
		Transaction newTransaction = transactionService.createTransaction(t);
		TransactionResponseDTO dto = transactionDTOMapper.getTransactionUsingDTO(newTransaction);
		return new ResponseEntity<TransactionResponseDTO>(dto, HttpStatus.CREATED);
	}

	@GetMapping("/findTransactionById")
	public ResponseEntity<TransactionResponseDTO> findTransactionById(@RequestParam long transaction_id)
			throws DetailsNotFoundException {
		Transaction saved = transactionService.findTransactionById(transaction_id);
		TransactionResponseDTO dto = transactionDTOMapper.getTransactionUsingDTO(saved);
		return new ResponseEntity<TransactionResponseDTO>(dto, HttpStatus.FOUND);
	}

	@GetMapping("/getAllMyAccTransactions")
	public ResponseEntity<List<TransactionResponseDTO>> getAllMyAccTransactions(long account_id)
			throws InvalidAccountException, EmptyListException {
		List<Transaction> allMyTransactions = transactionService.getAllMyAccTransactions(account_id);
		List<TransactionResponseDTO> temp = new ArrayList<TransactionResponseDTO>();
		for (Transaction transaction : allMyTransactions) {
			temp.add(transactionDTOMapper.getTransactionUsingDTO(transaction));
		}
		return new ResponseEntity<List<TransactionResponseDTO>>(temp, HttpStatus.FOUND);
	}

	@GetMapping("/listAllTransactions")
	public ResponseEntity<List<TransactionResponseDTO>> listAllTransactions(long accountId, String from, String to)
			throws InvalidAccountException, EmptyListException {
		List<Transaction> betweenTransaction = transactionService.listAllTransactions(accountId, from, to);
		List<TransactionResponseDTO> temp = new ArrayList<TransactionResponseDTO>();
		for (Transaction transaction : betweenTransaction) {
			temp.add(transactionDTOMapper.getTransactionUsingDTO(transaction));
		}
		return new ResponseEntity<List<TransactionResponseDTO>>(temp, HttpStatus.FOUND);
	}
	
	@PutMapping("/withdraw")
	public String withdraw(@RequestParam long accounId,@RequestParam double amount,@RequestBody Transaction t) throws InvalidAccountException, LowBalanceException, InvalidDetailsException {
		Transaction transaction=transactionService.withdraw(accounId, amount, t);
		return "You've Withdrawed "+amount+" from your account "+accounId;
		
	}

	

}
