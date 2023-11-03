package com.cg.iba.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.iba.entity.Account;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

	@PostMapping("/register/account")
	public int saveAccount(@RequestBody Account a)
	{
		 return 12345;
	}
}
