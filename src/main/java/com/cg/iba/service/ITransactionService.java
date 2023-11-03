package com.cg.iba.service;

import java.time.LocalDate;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.cg.iba.entity.Transaction;
import com.cg.iba.exception.DetailsNotFoundException;
import com.cg.iba.exception.EmptyListException;
import com.cg.iba.exception.InvalidAccountException;
import com.cg.iba.exception.InvalidDetailsException;

@Service
public interface ITransactionService {

	public Transaction createTransaction(Transaction transaction) throws InvalidDetailsException;
	public Transaction viewTransaction(long transaction_id) throws DetailsNotFoundException;
	public Transaction findTransactionById(long transaction_id) throws DetailsNotFoundException;
	public  Set<Transaction> listAllTransactions(long accountId,LocalDate from, LocalDate to) throws
													InvalidAccountException,EmptyListException;
	public  Set<Transaction> getAllMyAccTransactions(int account_id) throws 
							InvalidAccountException,EmptyListException;
}
