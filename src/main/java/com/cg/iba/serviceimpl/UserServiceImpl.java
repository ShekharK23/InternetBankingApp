package com.cg.iba.serviceimpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.iba.entity.Account;
import com.cg.iba.entity.Admin;
import com.cg.iba.entity.User;
import com.cg.iba.entity.enums.Role;
import com.cg.iba.exception.DetailsNotFoundException;
import com.cg.iba.exception.InvalidAccountException;
import com.cg.iba.exception.InvalidDetailsException;
import com.cg.iba.repository.IAccountRepository;
import com.cg.iba.repository.IAdminRepository;
import com.cg.iba.repository.IUserRepository;
import com.cg.iba.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	IAccountRepository accountRepository;
	
	@Autowired
	IAdminRepository adminRepository;

	@Override
	public User addNewUser(User user) throws InvalidDetailsException {
		if (user != null) {
			User savedUser = userRepository.save(user);
			return savedUser;
		} else {
			throw new InvalidDetailsException("Invalid Details.", UserServiceImpl.class+"");
		}
	}

	@Override
	public User signIn(User user) throws InvalidDetailsException {
		User u = userRepository.findById(user.getUserId())
				.orElseThrow(() -> new InvalidDetailsException("Invalid UserID Entered.", UserServiceImpl.class+""));
		if(u.getPassword().equals(user.getPassword()) && u.getRole().equals(user.getRole())) {
			return u;
		} else {
			throw new InvalidDetailsException("Enter Correct Password.", UserServiceImpl.class+"");
		}
	}

	@Override
	public User signOut(User user) throws InvalidDetailsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public User updateUserInfo(Long id, User user) throws InvalidDetailsException {
		User userDetails = userRepository.findById(id)
				.orElseThrow(() -> new InvalidDetailsException("Invalid UserId Entered.", UserServiceImpl.class+""));
		userDetails.setPassword(user.getPassword());
		return userDetails;
	}

	@Override
	public boolean deleteUserInfo(long userId) throws DetailsNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new DetailsNotFoundException("Details Not found for entered UserId.", UserServiceImpl.class+""));
		if(user != null) {
			userRepository.deleteById(user.getUserId());
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public User allocateAccountToUser(long accNum, long userId) throws InvalidAccountException, InvalidDetailsException {
		Account account = accountRepository.findById(accNum)
				.orElseThrow(() -> new InvalidAccountException("Enter Correct Account Number.", UserServiceImpl.class+""));
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new InvalidDetailsException("Enter Correct Details of User.", UserServiceImpl.class+""));
		if(account != null && user != null && user.getRole() == Role.CUSTOMER)
		{
			user.setAccount(account);
			return user;
		}
		return null;
	}
	
	@Override
	@Transactional
	public User allocateAdminToUser(long adminId, long userId) throws InvalidAccountException, InvalidDetailsException {
		Admin admin = adminRepository.findById(adminId)
				.orElseThrow(() -> new InvalidAccountException("Enter Correct AdminID.", UserServiceImpl.class+""));
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new InvalidDetailsException("Enter Correct Details of User.", UserServiceImpl.class+""));
		if(admin != null && user != null && user.getRole() == Role.ADMIN)
		{
			user.setAdmin(admin);
			return user;
		}
		return null;
	}
}
