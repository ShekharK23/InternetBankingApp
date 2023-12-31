package com.cg.iba.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.cg.iba.entity.Admin;

import com.cg.iba.exception.DetailsNotFoundException;
import com.cg.iba.exception.EmptyListException;
import com.cg.iba.exception.InvalidDetailsException;

@Service
public interface IAdminService {

	public Admin  addAdmin(Admin admin) throws InvalidDetailsException;
	public Admin findAdminById(long adminId)throws DetailsNotFoundException ;
	public Admin updateAdmin(Admin admin) throws InvalidDetailsException ;
	public boolean removeAdmin(long adminId)throws DetailsNotFoundException;
	public List<Admin> listAllAdmins() throws EmptyListException;
}
