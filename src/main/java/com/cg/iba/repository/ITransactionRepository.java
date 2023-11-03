package com.cg.iba.repository;


import java.time.LocalDate;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.iba.entity.Transaction;
import com.cg.iba.exception.DetailsNotFoundException;
import com.cg.iba.exception.EmptyListException;
import com.cg.iba.exception.InvalidAccountException;
import com.cg.iba.exception.InvalidDetailsException;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Long> {

//    public Transaction createTransaction(Transaction transaction) throws InvalidDetailsException;
//	public Transaction viewTransaction(long transaction_id) throws DetailsNotFoundException;
//	public Transaction findTransactionById(long transaction_id) throws DetailsNotFoundException;
//	public  Set<Transaction> listAllTransactions(LocalDate from, LocalDate to) throws EmptyListException;
//	public  Set<Transaction> getAllMyAccTransactions(int account_id) throws InvalidAccountException,EmptyListException;
}
