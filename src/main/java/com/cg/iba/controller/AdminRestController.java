package com.cg.iba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.iba.dto.CurrentAccountRequestSubmitDTO;
import com.cg.iba.dto.SavingAccountRequestSubmitDTO;
import com.cg.iba.entity.Account;
import com.cg.iba.entity.CurrentAccount;
import com.cg.iba.entity.SavingsAccount;
import com.cg.iba.exception.InvalidDetailsException;
import com.cg.iba.service.IAccountService;
import com.cg.iba.util.CurrentAccountRequestSubmitDTOConverter;
import com.cg.iba.util.SavingsAccountRequestSubmitDTOConverter;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

	@Autowired
	IAccountService accountService;
	
	@Autowired
	SavingsAccountRequestSubmitDTOConverter savAccReqSubDTO;
	
	@Autowired
	CurrentAccountRequestSubmitDTOConverter curAccReqSubDTO;
	
	@PostMapping("/register/newsavingsdto") // working
	public SavingsAccount saveSavingsAccountDto(@RequestBody SavingAccountRequestSubmitDTO dto) throws InvalidDetailsException
	{
		SavingsAccount a = savAccReqSubDTO.setAccountUsingDTO(dto);
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
	
    @PutMapping("/savings/{accountId}") // working
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
}
