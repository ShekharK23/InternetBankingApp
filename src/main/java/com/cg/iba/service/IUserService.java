package com.cg.iba.service;

import org.springframework.stereotype.Service;

import com.cg.iba.entity.User;
import com.cg.iba.exception.DetailsNotFoundException;
import com.cg.iba.exception.InvalidDetailsException;

@Service
public interface IUserService {

	public  User addNewUser(User user) throws InvalidDetailsException;
	public User signIn(User user)throws InvalidDetailsException;
	public User signOut(User user) throws InvalidDetailsException;
	public User updateUserInfo(User user) throws InvalidDetailsException;
	public boolean deleteUserInfo(long userId)throws DetailsNotFoundException;

}
