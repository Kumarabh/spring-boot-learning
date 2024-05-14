package com.boot.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.application.dao.CustomerRepository;
import com.boot.application.entities.Customer;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	public List<Customer> getAll() {
		return this.customerRepository.findAll();
	}
	
	public Customer getById(int id) {
		Customer customer = null;
		
		try {
			customer = this.customerRepository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return customer;
	}
	
	public Customer create(Customer c) {
		return this.customerRepository.save(c);
	}
	
	public Customer update(Customer c, int id) {
		
		try {
			Customer customer = this.customerRepository.findById(id).orElseThrow(() -> new Exception("Resource not found."));
			System.out.println(customer);
			if(customer == null) {
			  throw new Exception("Customer does not exists.");
			}
			return this.customerRepository.save(c);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public void deleteById(int id) {
		this.customerRepository.deleteById(id);
		
	}
}
