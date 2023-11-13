package com.cg.iba.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

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

import com.cg.iba.dto.AccountResponseDTO;
import com.cg.iba.dto.AdminRequestSubmitDTO;
import com.cg.iba.dto.AdminResponseDTO;
import com.cg.iba.dto.CurrentAccountRequestSubmitDTO;
import com.cg.iba.dto.CurrentAccountResponseDTO;
import com.cg.iba.dto.NomineeRequestSubmitDTO;
import com.cg.iba.dto.SavingAccountRequestSubmitDTO;
import com.cg.iba.dto.SavingAccountResponseDTO;
import com.cg.iba.dto.UserResponseDTO;
import com.cg.iba.entity.Account;
import com.cg.iba.entity.Admin;
import com.cg.iba.entity.CurrentAccount;
import com.cg.iba.entity.DebitCard;
import com.cg.iba.entity.Nominee;
import com.cg.iba.entity.SavingsAccount;
import com.cg.iba.entity.User;
import com.cg.iba.exception.DetailsNotFoundException;
import com.cg.iba.exception.EmptyListException;
import com.cg.iba.exception.InvalidAccountException;
import com.cg.iba.exception.InvalidDetailsException;
import com.cg.iba.service.IAccountService;
import com.cg.iba.service.IAdminService;
import com.cg.iba.service.IDebitCardService;
import com.cg.iba.service.INomineeService;
import com.cg.iba.service.IUserService;
import com.cg.iba.util.AccountDTOMapper;
import com.cg.iba.util.AdminDTOMapper;
import com.cg.iba.util.CurrentAccountDTOMapper;
import com.cg.iba.util.NomineeDTOMapper;
import com.cg.iba.util.SavingsAccountDTOMapper;
import com.cg.iba.util.UserDTOMapper;

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
	IUserService userService;
	
	@Autowired
	IAdminService adminService;
	
	@Autowired
	SavingsAccountDTOMapper savAccDTO;
	
	@Autowired
	CurrentAccountDTOMapper curAccDTO;
	
	@Autowired
	NomineeDTOMapper nomineeDTO;
	
	@Autowired
	AccountDTOMapper accountDTO;
	
	@Autowired
	AdminDTOMapper adminDTO;
	
	@Autowired
	UserDTOMapper userDTO;
	
	@PostMapping("/register/newsavingsdto") // to be checked
	public ResponseEntity<SavingAccountResponseDTO> saveSavingsAccountDto(@RequestBody SavingAccountRequestSubmitDTO dto) throws InvalidDetailsException
	{
		SavingsAccount a = savAccDTO.setSavingAccountUsingDTO(dto);
		SavingsAccount sav =  accountService.addSavingsAccount(a);
		SavingAccountResponseDTO resDto = savAccDTO.getSavingAccountUsingDTO(sav);
		return new ResponseEntity<SavingAccountResponseDTO>(resDto,HttpStatus.OK);
	}
	
	@PostMapping("/register/newcurrentdto") // to be checked
	public ResponseEntity<CurrentAccountResponseDTO> saveCurrentAcoountDto(@RequestBody CurrentAccountRequestSubmitDTO dto) throws InvalidDetailsException
	{
		CurrentAccount a = curAccDTO.setCurrentAccountUsingDTO(dto);
		CurrentAccount cur =  accountService.addCurrentAccount(a);
		CurrentAccountResponseDTO resDto = curAccDTO.getCurrentAccountUsingDTO(cur);
		return new ResponseEntity<CurrentAccountResponseDTO>(resDto,HttpStatus.OK);
	}
	
    @PutMapping("/savings/{accountId}") // to be checked
    public ResponseEntity<SavingAccountResponseDTO> updateSavingsAccount(
            @PathVariable long accountId,
            @RequestBody SavingAccountRequestSubmitDTO savingRequestDTO
    ) {
        try {
            SavingsAccount updatedAccount = accountService.updateSavingsAccount(accountId, savingRequestDTO);
            SavingAccountResponseDTO dto = savAccDTO.getSavingAccountUsingDTO(updatedAccount);
            return new ResponseEntity<SavingAccountResponseDTO>(dto, HttpStatus.OK);
        } catch (InvalidDetailsException e) {
            return new ResponseEntity<SavingAccountResponseDTO>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/current/{accountId}") // to be checked
    public ResponseEntity<CurrentAccountResponseDTO> updateCurrentAccount(
            @PathVariable long accountId,
            @RequestBody CurrentAccountRequestSubmitDTO currentRequestDTO
    ) {
        try {
            CurrentAccount updatedAccount = accountService.updateCurrentAccount(accountId, currentRequestDTO);
            CurrentAccountResponseDTO res = curAccDTO.getCurrentAccountUsingDTO(updatedAccount);
            return new ResponseEntity<CurrentAccountResponseDTO>(res, HttpStatus.OK);
        } catch (InvalidDetailsException e) {
            return new ResponseEntity<CurrentAccountResponseDTO>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/saving/delete")
    public ResponseEntity<String> deleteSavingAccount(@RequestBody SavingsAccount savingAccount) {
    	try {
    		boolean status = accountService.closeSavingsAccount(savingAccount);
    		if(status) {
    			String response = "Saving account - "+savingAccount.getAccountId()+" is Closed.";
    			return new ResponseEntity<String>(response,HttpStatus.OK);
    		}
		} catch (InvalidAccountException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
    	return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping("/current/delete")
    public ResponseEntity<String> deleteCurrentAccount(@RequestBody CurrentAccount currentAccount) {
    	try {
    		boolean status = accountService.closeCurrentAccount(currentAccount);
    		if(status) {
    			String response = "Current account - "+currentAccount.getAccountId()+" is Closed.";
    			return new ResponseEntity<String>(response,HttpStatus.OK);
    		}
		} catch (InvalidAccountException e) {
			System.out.println(e);
		}
    	return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
    
	@GetMapping("/getaccount") // to be checked
	public ResponseEntity<Account> getAccountById(@RequestParam long accountId)
	{
		try {
			Account account = accountService.findAccountById(accountId);
			//AccountResponseDTO dto = accountDTO.getAccountUsingDTO(account);
			return new ResponseEntity<Account>(account,HttpStatus.OK);
		} catch (InvalidAccountException e) {
			System.out.println(e);
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping("/getallaccounts") // to be check
	public ResponseEntity<List<AccountResponseDTO>> getAllAccounts()
	{
		List<Account> accounts = accountService.viewAccounts();
		List<AccountResponseDTO> list = new ArrayList<AccountResponseDTO>();
		for (Account account : accounts) {
			list.add(accountDTO.getAccountUsingDTO(account));
		}
		return new ResponseEntity<List<AccountResponseDTO>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/getsavingaccount") // to be check
	public ResponseEntity<SavingAccountResponseDTO> getSavingAccountById(@RequestParam long accountId) throws DetailsNotFoundException
	{
		try {
			SavingsAccount account = accountService.viewSavingAcc(accountId);
			SavingAccountResponseDTO dto = savAccDTO.getSavingAccountUsingDTO(account);
			return new ResponseEntity<SavingAccountResponseDTO>(dto,HttpStatus.OK);
		} catch (DetailsNotFoundException e) {
			System.out.println(e);
			return new ResponseEntity<SavingAccountResponseDTO>(HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping("/getcurrentaccount") // to be check
	public ResponseEntity<CurrentAccountResponseDTO> getCurrentAccountById(@RequestParam long accountId) throws DetailsNotFoundException
	{
		try {
			CurrentAccount account = accountService.viewCurrentAcc(accountId);
			CurrentAccountResponseDTO dto = curAccDTO.getCurrentAccountUsingDTO(account);
			return new ResponseEntity<CurrentAccountResponseDTO>(dto,HttpStatus.OK);
		} catch (DetailsNotFoundException e) {
			System.out.println(e);
			return new ResponseEntity<CurrentAccountResponseDTO>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PutMapping("/debitcardtoacc")
	public ResponseEntity<Account> allocateDebitCardToAccount(@RequestParam long accNum,@RequestParam long debitCardNum) throws InvalidAccountException {
		Account updatedAcc = accountService.addDebitCardToAccount(accNum, debitCardNum);
		return new ResponseEntity<Account>(updatedAcc,HttpStatus.OK);
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
		Nominee n = nomineeDTO.setNomineeUsingDTO(nominee);
		Nominee savedNominee = nomineeService.addNominee(n);
		return savedNominee;
	}
 
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
	
	@PutMapping("/policytoaccount")
	public Account allocatePolicyToAccount(@RequestParam long policyId,@RequestParam long accNum) throws InvalidAccountException, InvalidDetailsException,DetailsNotFoundException {

		Account updatedAccount = accountService.addPolicyToAccount(policyId, accNum);
		return updatedAccount;
	}
	
	@PutMapping("/beneficiarytoaccount")
	public Account allocateBeneficiaryToAccount(@RequestParam long beneficiaryId,@RequestParam long accNum) throws InvalidAccountException, InvalidDetailsException,DetailsNotFoundException {

		Account updatedAccount = accountService.addBeneficiaryToAccount(beneficiaryId, accNum);
		return updatedAccount;
	}
	
	@PutMapping("/transactiontoaccount")
	public Account allocateTransactionToAccount(@RequestParam long transactionId,@RequestParam long accNum) throws InvalidAccountException, InvalidDetailsException,DetailsNotFoundException {

		Account updatedAccount = accountService.addTransactionToAccount(transactionId, accNum);
		return updatedAccount;
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// Admin work	
	
	@PostMapping("/register/newadmin") // to be checked
	public ResponseEntity<AdminResponseDTO> saveAdmin(@RequestBody AdminRequestSubmitDTO dto) throws InvalidDetailsException
	{
		Admin a = adminDTO.setAdminUsingDTO(dto);
		Admin a1 =  adminService.addAdmin(a);
		AdminResponseDTO resDto = adminDTO.getAdminUsingDTO(a1);
		return new ResponseEntity<AdminResponseDTO>(resDto,HttpStatus.OK);
	}
	
	@GetMapping("/findAdminById")
	public ResponseEntity<AdminResponseDTO> findAdminById(@RequestParam long adminId) throws DetailsNotFoundException {
 
		Admin savedAdmin = adminService.findAdminById(adminId);
		AdminResponseDTO dto = adminDTO.getAdminUsingDTO(savedAdmin);
		return new ResponseEntity<AdminResponseDTO>(dto, HttpStatus.FOUND);
	}
	
	@PutMapping("/updateadmin")
	public ResponseEntity<AdminResponseDTO> updateAdmin(@RequestParam long adminId, @RequestBody AdminRequestSubmitDTO dto) throws InvalidDetailsException {
		Admin a = adminService.updateAdmin(adminId, dto);
		AdminResponseDTO dto1 = adminDTO.getAdminUsingDTO(a);
		return new ResponseEntity<AdminResponseDTO>(dto1, HttpStatus.FOUND);
	}
	
	@DeleteMapping("/removeadmin")
	public ResponseEntity<Boolean> removeAdmin(@RequestParam long adminId) throws DetailsNotFoundException {
		boolean status = adminService.removeAdmin(adminId);
		if (status) {
			return new ResponseEntity<Boolean>(status, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getalladmins")
	public ResponseEntity<List<AdminResponseDTO>> listAllAdmins() throws EmptyListException {
		List<Admin> allAdmins = adminService.listAllAdmins();
		List<AdminResponseDTO> newlist = allAdmins.stream()
		    .map(admin -> adminDTO.getAdminUsingDTO(admin))
		    .collect(Collectors.toList());
		return new ResponseEntity<List<AdminResponseDTO>>(newlist,HttpStatus.OK);
	}
	
	@PutMapping("/accounttouser")
	public ResponseEntity<UserResponseDTO> allocateAccountToUser(@RequestParam long accNum,@RequestParam long userId) throws InvalidAccountException, InvalidDetailsException {
		User user = userService.allocateAccountToUser(accNum, userId);
		UserResponseDTO dto = userDTO.getUserUsingDTO(user);
		return new ResponseEntity<UserResponseDTO>(dto,HttpStatus.OK);
	}
	
	@PutMapping("/admintouser")
	public ResponseEntity<UserResponseDTO> allocateAdminToUser(@RequestParam long adminId,@RequestParam long userId) throws InvalidAccountException, InvalidDetailsException {
		User user = userService.allocateAdminToUser(adminId, userId);
		UserResponseDTO dto = userDTO.getUserUsingDTO(user);
		return new ResponseEntity<UserResponseDTO>(dto,HttpStatus.OK);
	}
	
}//end class
