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
		return this.customerRepository.findById(id).get();
	}
	
	public Customer create(Customer c) {
		return this.customerRepository.save(c);
	}
	
	public Customer update(Customer c) {
		return this.customerRepository.save(c);
	}
	
	public void deleteById(int id) {
		this.customerRepository.deleteById(id);
		
	}
}
