package com.boot.application.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boot.application.CustomerService;
import com.boot.application.entities.Customer;

@RestController
public class TestController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(path = "/test1", method = RequestMethod.GET)
	public String test1() {
		return "Test-1 works";
	}
	
	@GetMapping("/test2")
	public String test() {
		return "Test-2 works";
		
	}

	@GetMapping("/customer")
	public List<Customer> getCustomer() {
		return this.customerService.getAllCustomers();
	}
	
	@GetMapping("/customer/{customerId}")
	public Customer getCustomer(@PathVariable("customerId") int customerId) {
		return this.customerService.getCustomerById(customerId);
		
	}
	
	@PostMapping("/customer")
	public Customer createCustomer(@RequestBody Customer customer) {
		return this.customerService.createCustomer(customer);
		
	}
	
	
}
