package com.cg.iba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.iba.entity.User;
import com.cg.iba.exception.DetailsNotFoundException;
import com.cg.iba.exception.InvalidDetailsException;

@Repository
public interface IUserRepository extends JpaRepository<User, Long>{
	
	public  User addNewUser(User user) throws InvalidDetailsException;
	public User signIn(User user)throws InvalidDetailsException;
	public User signOut(User user) throws InvalidDetailsException;
	public User updateUserInfo(User user) throws InvalidDetailsException;
	public boolean deleteUserInfo(long userId)throws DetailsNotFoundException;
}
