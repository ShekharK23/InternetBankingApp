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

import com.cg.iba.dto.CurrentAccountRequestSubmitDTO;
import com.cg.iba.dto.NomineeRequestSubmitDTO;
import com.cg.iba.dto.NomineeResponseDTO;
import com.cg.iba.dto.SavingAccountRequestSubmitDTO;
import com.cg.iba.dto.TransactionRequestDTO;
import com.cg.iba.entity.Account;
import com.cg.iba.entity.CurrentAccount;
import com.cg.iba.entity.DebitCard;
import com.cg.iba.entity.Nominee;
import com.cg.iba.entity.SavingsAccount;
import com.cg.iba.entity.Transaction;
import com.cg.iba.exception.DetailsNotFoundException;
import com.cg.iba.exception.EmptyListException;
import com.cg.iba.exception.InvalidAccountException;
import com.cg.iba.exception.InvalidAmountException;
import com.cg.iba.exception.InvalidDetailsException;
import com.cg.iba.service.IAccountService;
import com.cg.iba.service.IDebitCardService;
import com.cg.iba.service.INomineeService;
import com.cg.iba.util.CurrentAccountRequestSubmitDTOConverter;
import com.cg.iba.util.NomineeDTOMapper;
import com.cg.iba.util.SavingsAccountRequestSubmitDTOConverter;
import com.cg.iba.util.TransactionDTOMapper;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

	@Autowired
	IAccountService accountService;
	
	@Autowired
	IDebitCardService debitCardService;
	
	
	
	@Autowired
	SavingsAccountRequestSubmitDTOConverter savAccReqSubDTO;
	
	@Autowired
	CurrentAccountRequestSubmitDTOConverter curAccReqSubDTO;
	
	@Autowired
	TransactionDTOMapper transactionDTOMapper;
	
	
	
	@PostMapping("/register/newsavingsdto") // working
	public SavingsAccount saveSavingsAccountDto(@RequestBody SavingAccountRequestSubmitDTO dto) throws InvalidDetailsException
	{
		SavingsAccount a = savAccReqSubDTO.setSavingAccountUsingDTO(dto);
		SavingsAccount sav =  accountService.addSavingsAccount(a);
		return sav;
	}
	
	@PostMapping("/register/newcurrentdto") // working
	public CurrentAccount saveCurrentAcoountDto(@RequestBody CurrentAccountRequestSubmitDTO dto) throws InvalidDetailsException
	{
		CurrentAccount a = curAccReqSubDTO.setCurrentAccountUsingDTO(dto);
		CurrentAccount cur =  accountService.addCurrentAccount(a);
		return cur;
	}
	
    @PutMapping("/updateSavingsAccount/{accountId}") // working
    public ResponseEntity<SavingsAccount> updateSavingsAccount(
            @PathVariable long accountId,
            @RequestBody SavingAccountRequestSubmitDTO savingRequestDTO
    ) {
        try {
            SavingsAccount updatedAccount = accountService.updateSavingsAccount(accountId, savingRequestDTO);
            return new ResponseEntity<SavingsAccount>(updatedAccount, HttpStatus.OK);
        } catch (InvalidDetailsException e) {
            return new ResponseEntity<SavingsAccount>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/updateCurrentAccount/{accountId}") // working
    public ResponseEntity<CurrentAccount> updateCurrentAccount(
            @PathVariable long accountId,
            @RequestBody CurrentAccountRequestSubmitDTO currentRequestDTO
    ) {
        try {
            CurrentAccount updatedAccount = accountService.updateCurrentAccount(accountId, currentRequestDTO);
            return new ResponseEntity<CurrentAccount>(updatedAccount, HttpStatus.OK);
        } catch (InvalidDetailsException e) {
            return new ResponseEntity<CurrentAccount>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/deleteSavingAccount")
    public String deleteSavingAccount(@RequestParam long accountNo) {
    	try {
    		String status = accountService.closeSavingsAccount(accountNo);
    		return status;
    		}
    		catch (InvalidAccountException e) {
			System.out.println(e);
		}
    	return "Saving Account Not Closed.";
    }
    
//    @DeleteMapping("/deleteCurrentAccount")
//    public String deleteCurrentAccount(@RequestBody CurrentAccount currentAccount) {
//    	try {
//    		boolean status = accountService.closeCurrentAccount(currentAccount);
//    		if(status) {
//    			return "Current account - "+currentAccount.getAccountId()+" is Closed.";
//    		}
//		} catch (InvalidAccountException e) {
//			System.out.println(e);
//		}
//    	return "Current Account Not Closed.";
//    }
    
	@GetMapping("/getAccountById1") // working
	public ResponseEntity<Account> getAccountById1(@RequestParam long accountId)
	{
		try {
			Account account = accountService.findAccountById(accountId);
			return new ResponseEntity<Account>(account,HttpStatus.OK);
		} catch (InvalidAccountException e) {
			System.out.println(e);
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping("/getallaccounts") // working
	public ResponseEntity<List<Account>> getAllAccounts()
	{
		List<Account> accounts = accountService.viewAccounts();
		return new ResponseEntity<List<Account>>(accounts, HttpStatus.OK);
	}
	
	@GetMapping("/getSavingAccountById") // working
	public ResponseEntity<SavingsAccount> getSavingAccountById(@RequestParam long accountId) throws DetailsNotFoundException
	{
		try {
			SavingsAccount account = accountService.viewSavingAcc(accountId);
			return new ResponseEntity<SavingsAccount>(account,HttpStatus.OK);
		} catch (DetailsNotFoundException e) {
			System.out.println(e);
			return new ResponseEntity<SavingsAccount>(HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping("/getCurrentAccountById") // working
	public ResponseEntity<CurrentAccount> getCurrentAccountById(@RequestParam long accountId) throws DetailsNotFoundException
	{
		try {
			CurrentAccount account = accountService.viewCurrentAcc(accountId);
			return new ResponseEntity<CurrentAccount>(account,HttpStatus.OK);
		} catch (DetailsNotFoundException e) {
			System.out.println(e);
			return new ResponseEntity<CurrentAccount>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PutMapping("/allocateDebitCardToAccount")//working
	public Account allocateDebitCardToAccount(@RequestParam long accNum,@RequestParam long debitCardNum) throws InvalidAccountException {
//		try {
//			Account updatedAcc = accountService.addDebitCardToAccount(accNum, debitCardNum);
//			return new ResponseEntity<Account>(updatedAcc,HttpStatus.OK);
//		} catch (Exception e) {
//			System.out.println(e);
//			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
//		}
		Account updatedAcc = accountService.addDebitCardToAccount(accNum, debitCardNum);
		return updatedAcc;
	}
	
	// Debit cards functionality
    @PostMapping("/createDebitCard")//working
    public long createDebitCard(@RequestBody DebitCard debitCard) {
        return debitCardService.saveDebitCardDetails(debitCard);
    }

    @GetMapping("getDebitCard/{debitCardNumber}")//working
    public DebitCard getDebitCard(@PathVariable long debitCardNumber) {
        return debitCardService.getDebitCardByDebitCardNumber(debitCardNumber);
    }

    @PutMapping("changePin/{debitCardNumber}/change-pin")//working
    public DebitCard changePin(@PathVariable long debitCardNumber, @RequestParam int newPin) {
        return debitCardService.changePin(debitCardNumber, newPin);
    }

    @GetMapping("checkExpiry/{debitCardNumber}/check-expiry")//logic is not correct
    public String checkExpiry(@PathVariable long debitCardNumber) {
        return debitCardService.checkExpiry(debitCardNumber);
    }

    @PutMapping("/{debitCardNumber}/request-new-card")//not done yet..
    public DebitCard requestNewCard(@PathVariable long debitCardNumber) {
        return debitCardService.requestNewCard(debitCardNumber);
    }
    
    //Nominee Services
 
	
	
//	@PostMapping("/registernewdto") // working
//	public long saveAccountdto(@RequestBody AccountAddDTO dto)
//	{
//		Account a = accountAddConverter.getAccountFromAccountDTO(dto);
//		long newId =  accountService.saveAccounts(a);
//		return newId;
//	}
 
	
 
	
	
	
	
	
	@PutMapping("/allocateNomineeToAccount")//Working
	public Account allocateNomineeToAccount(@RequestParam long nomineeId,@RequestParam long accNum) throws InvalidAccountException, InvalidDetailsException,DetailsNotFoundException {

		Account updatedAccount = accountService.addNomineeToAccount(nomineeId, accNum);
		return updatedAccount;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	
//	@PutMapping("/updateNomineeDetails") // working
//	public ResponseEntity<NomineeResponseDTO> updateNominee(@RequestParam long id, @RequestBody NomineeRequestSubmitDTO nominee)
//			throws InvalidDetailsException, DetailsNotFoundException {
//		Nominee updatedNominee = nomineeService.updateNominee(id, nominee);
//		NomineeResponseDTO n=nomineeDTOMapper.getNomineeUsingDTO(updatedNominee);
//		
//		return new ResponseEntity<NomineeResponseDTO>(n, HttpStatus.FOUND);
//	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@PutMapping("/deposit")
	public ResponseEntity<Transaction>  deposit(@RequestParam long accounId,@RequestParam double amount,@RequestBody Transaction transaction) throws InvalidAccountException, InvalidAmountException, InvalidDetailsException {
		Transaction saved=accountService.deposit(accounId, amount, transaction);
		return new ResponseEntity<Transaction>(saved, HttpStatus.OK);
	}
}
