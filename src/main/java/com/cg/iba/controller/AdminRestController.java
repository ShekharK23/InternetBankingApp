package com.cg.iba.controller;

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
import com.cg.iba.dto.SavingAccountRequestSubmitDTO;
import com.cg.iba.entity.Account;
import com.cg.iba.entity.CurrentAccount;
import com.cg.iba.entity.DebitCard;
import com.cg.iba.entity.Nominee;
import com.cg.iba.entity.SavingsAccount;
import com.cg.iba.exception.DetailsNotFoundException;
import com.cg.iba.exception.EmptyListException;
import com.cg.iba.exception.InvalidAccountException;
import com.cg.iba.exception.InvalidDetailsException;
import com.cg.iba.service.IAccountService;
import com.cg.iba.service.IDebitCardService;
import com.cg.iba.service.INomineeService;
import com.cg.iba.util.CurrentAccountRequestSubmitDTOConverter;
import com.cg.iba.util.NomineeRequestSubmitDTOMapper;
import com.cg.iba.util.SavingsAccountRequestSubmitDTOConverter;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

	@Autowired
	IAccountService accountService;
	
	@Autowired
	IDebitCardService debitCardService;
	
	@Autowired
	INomineeService nomineeService;
	
	@Autowired
	SavingsAccountRequestSubmitDTOConverter savAccReqSubDTO;
	
	@Autowired
	CurrentAccountRequestSubmitDTOConverter curAccReqSubDTO;
	
	@Autowired
	NomineeRequestSubmitDTOMapper nomineeReqSubDTO;
	
	@PostMapping("/register/newsavingsdto") // to be checked
	public SavingsAccount saveSavingsAccountDto(@RequestBody SavingAccountRequestSubmitDTO dto) throws InvalidDetailsException
	{
		SavingsAccount a = savAccReqSubDTO.setSavingAccountUsingDTO(dto);
		SavingsAccount sav =  accountService.addSavingsAccount(a);
		return sav;
	}
	
	@PostMapping("/register/newcurrentdto") // to be checked
	public CurrentAccount saveCurrentAcoountDto(@RequestBody CurrentAccountRequestSubmitDTO dto) throws InvalidDetailsException
	{
		CurrentAccount a = curAccReqSubDTO.setCurrentAccountUsingDTO(dto);
		CurrentAccount cur =  accountService.addCurrentAccount(a);
		return cur;
	}
	
    @PutMapping("/savings/{accountId}") // to be checked
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
    
    @PutMapping("/current/{accountId}") // to be checked
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
    
    @DeleteMapping("/saving/delete")
    public String deleteSavingAccount(@RequestBody SavingsAccount savingAccount) {
    	try {
    		boolean status = accountService.closeSavingsAccount(savingAccount);
    		if(status) {
    			return "Saving account - "+savingAccount.getAccountId()+" is Closed.";
    		}
		} catch (InvalidAccountException e) {
			System.out.println(e);
		}
    	return "Saving Account Not Closed.";
    }
    
    @DeleteMapping("/current/delete")
    public String deleteCurrentAccount(@RequestBody CurrentAccount currentAccount) {
    	try {
    		boolean status = accountService.closeCurrentAccount(currentAccount);
    		if(status) {
    			return "Current account - "+currentAccount.getAccountId()+" is Closed.";
    		}
		} catch (InvalidAccountException e) {
			System.out.println(e);
		}
    	return "Current Account Not Closed.";
    }
    
	@GetMapping("/getaccount") // to be checked
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
	
	@GetMapping("/getallaccounts") // to be check
	public ResponseEntity<List<Account>> getAllAccounts()
	{
		List<Account> accounts = accountService.viewAccounts();
		return new ResponseEntity<List<Account>>(accounts, HttpStatus.OK);
	}
	
	@GetMapping("/getsavingaccount") // to be check
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
	
	@GetMapping("/getcurrentaccount") // to be check
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
	
	@PutMapping("/debitcardtoacc")
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
    @PostMapping("/createdebitcard")
    public long createDebitCard(@RequestBody DebitCard debitCard) {
        return debitCardService.saveDebitCardDetails(debitCard);
    }

    @GetMapping("/{debitCardNumber}")
    public DebitCard getDebitCard(@PathVariable long debitCardNumber) {
        return debitCardService.getDebitCardByDebitCardNumber(debitCardNumber);
    }

    @PutMapping("/{debitCardNumber}/change-pin")
    public DebitCard changePin(@PathVariable long debitCardNumber, @RequestParam int newPin) {
        return debitCardService.changePin(debitCardNumber, newPin);
    }

    @GetMapping("/{debitCardNumber}/check-expiry")
    public String checkExpiry(@PathVariable long debitCardNumber) {
        return debitCardService.checkExpiry(debitCardNumber);
    }

    @PutMapping("/{debitCardNumber}/request-new-card")
    public DebitCard requestNewCard(@PathVariable long debitCardNumber) {
        return debitCardService.requestNewCard(debitCardNumber);
    }
    
    //Nominee Services
 
	@PostMapping("/addNominee")
	public Nominee addNominee(@RequestBody NomineeRequestSubmitDTO nominee) throws InvalidDetailsException {
		Nominee n = nomineeReqSubDTO.setNomineeUsingDTO(nominee);
		Nominee savedNominee = nomineeService.addNominee(n);
		return savedNominee;
	}
	
//	@PostMapping("/registernewdto") // working
//	public long saveAccountdto(@RequestBody AccountAddDTO dto)
//	{
//		Account a = accountAddConverter.getAccountFromAccountDTO(dto);
//		long newId =  accountService.saveAccounts(a);
//		return newId;
//	}
 
	@GetMapping("/listAllNominee")
	public ResponseEntity<List<Nominee>> listAllNominees(@RequestParam long accountId) throws InvalidAccountException, EmptyListException {
		List<Nominee> allNominee = nomineeService.listAllNominees(accountId);
		return new ResponseEntity<List<Nominee>>(allNominee, HttpStatus.CREATED);
	}
 
	@GetMapping("/findNomineeById")
	public ResponseEntity<Nominee> findNomineeById(@RequestParam long nomineeId) throws DetailsNotFoundException {
 
		Nominee savedNominee = nomineeService.findNomineeById(nomineeId);
		return new ResponseEntity<Nominee>(savedNominee, HttpStatus.FOUND);
	}
	
	@DeleteMapping("/deleteNomineeById/{nomineeId}")
	public String deleteNomineeById(@PathVariable long nomineeId) throws DetailsNotFoundException {
		nomineeService.deleteNominee(nomineeId);
		return "The Nomineee is deleted for the id-:"+nomineeId;
	}
	
	@PutMapping("/updateNomineeDetails")
		public ResponseEntity<Nominee> updateNominee(@RequestBody Nominee nominee) throws InvalidDetailsException, DetailsNotFoundException {
		Nominee updatedNominee=nomineeService.updateNominee(nominee);
		return new ResponseEntity<Nominee>(updatedNominee, HttpStatus.CREATED);
	}
	
	@PutMapping("/nomineetoaccount")
	public Account allocateNomineeToAccount(@RequestParam long nomineeId,@RequestParam long accNum) throws InvalidAccountException, InvalidDetailsException,DetailsNotFoundException {

		Account updatedAccount = accountService.addNomineeToAccount(nomineeId, accNum);
		return updatedAccount;
	}
}
