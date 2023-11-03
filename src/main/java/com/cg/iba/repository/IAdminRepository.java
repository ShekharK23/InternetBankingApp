package com.cg.iba.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.iba.entity.Admin;

import com.cg.iba.exception.DetailsNotFoundException;
import com.cg.iba.exception.EmptyListException;
import com.cg.iba.exception.InvalidDetailsException;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, Long>{

	public Admin  addAdmin(Admin admin) throws InvalidDetailsException;
	public Admin findAdminById(long adminId)throws DetailsNotFoundException ;
	public Admin updateAdmin(Admin admin) throws InvalidDetailsException ;
	public boolean removeAdmin(long adminId)throws DetailsNotFoundException;
	public Set<Admin> listAllAdmins() throws EmptyListException;
}
