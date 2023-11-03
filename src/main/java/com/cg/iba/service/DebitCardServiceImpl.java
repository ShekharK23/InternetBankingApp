package com.cg.iba.service;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.cg.iba.entity.DebitCard;
import com.cg.iba.repository.IDebitCardRepository;
import com.cg.iba.util.DateConverter;

@Service
public class DebitCardServiceImpl implements IDebitCardService {
	
	@Autowired
	IDebitCardRepository debitCardRepository;

	@Override
	public long saveDebitCardDetails(DebitCard debitCard) {
		DebitCard savedDebitCard = debitCardRepository.save(debitCard);
		if(savedDebitCard != null) {
			long newDebitCardNumber = savedDebitCard.getDebitCardNumber();
			return newDebitCardNumber;
		}
		return 0;
	}

	@Override
	public DebitCard getDebitCardByDebitCardNumber(long debitCardNumber) {
		DebitCard savedDebitCard = debitCardRepository.findById(debitCardNumber).get();
		return savedDebitCard;
	}

	@Override
	@Transactional
	public DebitCard changePin(long debitCardNumber, int newPin) {
		DebitCard savedDebitCard = getDebitCardByDebitCardNumber(debitCardNumber);
		if(savedDebitCard != null) {
			savedDebitCard.setDebitCardPin(newPin);
			return savedDebitCard;
		}
		return null;
	}

//	@Override
//	@Transactional
//	public DebitCard blockCard(long debitCardNumber, String cardStatus) {
//		DebitCard savedDebitCard = getDebitCardByDebitCardNumber(debitCardNumber);
//		if(savedDebitCard!=null) {
//			savedDebitCard.setDebitCardStatus(cardStatus);
//			if(cardStatus.equalsIgnoreCase("Not Allocated")) {
//				savedDebitCard.setDebitCardStatus(cardStatus);
//			}
//			return savedDebitCard;
//		}
//		return null;
//	}


	  @Override
	  public String checkExpiry(long debitCardNumber) {
	        DebitCard savedDebitCard = getDebitCardByDebitCardNumber(debitCardNumber);
	        if (savedDebitCard != null) {
	            LocalDate currentDate = LocalDate.now();
	            String expiryDateString = savedDebitCard.getDebitCardExpiryDate();
	            
	            LocalDate expiryDate = DateConverter.getDateFromString(expiryDateString);
	            
	            if (expiryDate != null && currentDate.isAfter(expiryDate) ) {
	                return "Debit Card is Expired";
	            }
	            return "Debit Card is Yet To Expired";
	        }
	        return "Debit Card Not Allocated";
	    }

	@Override
	public DebitCard requestNewCard(long debitCardNumber) {
		DebitCard savedDebitCard = getDebitCardByDebitCardNumber(debitCardNumber);
		if(savedDebitCard!=null) {
			
		}
		return null;
	}
	  
	  
//	
}
