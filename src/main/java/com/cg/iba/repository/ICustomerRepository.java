package com.cg.iba.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.iba.entity.Customer;
import com.cg.iba.exception.DetailsNotFoundException;
import com.cg.iba.exception.EmptyListException;
import com.cg.iba.exception.InvalidAccountException;
import com.cg.iba.exception.InvalidDetailsException;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

	public Customer  addCustomer(Customer customer) throws InvalidDetailsException;
	public Customer updateCustomer(Customer customer)throws InvalidDetailsException ;
	public boolean deleteCustomer(long customer_id) throws DetailsNotFoundException;
	
	public Set<Customer> listAllCustomers(double minBalance) throws EmptyListException;
	public Customer viewCustomerDetails(long account_id) throws InvalidAccountException,DetailsNotFoundException ;
	
	public  Customer findCustomerById(long customer_id) throws DetailsNotFoundException;
}
