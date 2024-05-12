package com.boot.application.dao;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boot.application.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	// Derived Query - Hibernate generated implementations
	
	public List<Customer> findByGender(String gender); 
	
	public List<Customer> findByCustomerNameAndGender(String customerName, String gender);
	
	public List<Customer> findByCustomerNameStartingWith(String prefix);
	
	public List<Customer> findByCustomerNameEndingWith(String prefix);

	public List<Customer> findByCustomerNameIn(List<String> names);

	public List<Customer> findByCustomerNameContaining(String prefix);

	public List<Customer> findByCustomerNameLike(String pattern);
	
	public List<Customer> findByCustomerNameOrderByCustomerName(String pattern);

	public List<Customer> findByDateOfBirthLessThan(LocalDate dateOfBirth);

	public List<Customer> findByDateOfBirthLessThanEqual(LocalDate dateOfBirth);

	// JPQL - Java persistence query language
	
	@Query("SELECT c FROM Customer c")
	public List<Customer> getAllCustomers();
	
	@Query("SELECT c FROM Customer c WHERE c.gender = :param1")
	public List<Customer> getAllCustomersByGender(@Param("param1") String gender);
	
	@Query("SELECT c FROM Customer c WHERE c.customerName = :param1 and c.gender = :param2")
	public List<Customer> getAllCustomersByNamdAndGender(@Param("param1") String customerName, @Param("param2") String gender);
	
	// NATIVE QUERY (SQL)
	
	@Query(value = "SELECT * FROM customer", nativeQuery = true)
	public List<Customer> getCustomersList();
	
	
}
