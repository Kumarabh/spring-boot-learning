package com.boot.application.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.application.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	public List<Customer> findByGender(String gender); 
	
	public List<Customer> findByCustomerNameAndGender(String customerName, String gender);
	
	public List<Customer> findByCustomerNameStartingWith(String prefix);
	
	public List<Customer> findByCustomerNameEndingWith(String prefix);

	public List<Customer> findByCustomerNameIn(List<String> names);

	public List<Customer> findByCustomerNameContaining(String prefix);

	public List<Customer> findByCustomerNameLike(String pattern);
	
	public List<Customer> findByCustomerNameOrderByCustomerName(String pattern);

//	public List<Customer> findByAgeLessThan(int age);

//	public List<Customer> findByAgeLessThanEqual(int age);

}
