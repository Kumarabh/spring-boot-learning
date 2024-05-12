package com.boot.application.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boot.application.entities.Customer;
import com.boot.application.services.CustomerService;

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
	public ResponseEntity<List<Customer>> getCustomer() {
		List<Customer> customerList = this.customerService.getAllCustomers();
		
		if(customerList.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(customerList));
		
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("customerId") int customerId) {
		Customer customer = this.customerService.getCustomerById(customerId);
		
		if(customer == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(customer));
	}
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer c) {
		Customer customer = null;

		try {
			customer = this.customerService.createCustomer(c);
			return ResponseEntity.of(Optional.of(customer));
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	@DeleteMapping("/customer/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("customerId") int customerId) {
		try {
			this.customerService.deleteCustomer(customerId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}
	
	@PutMapping("/customer")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer c) {
		Customer customer = null;
		
		try {
			customer = this.customerService.updateCustomer(c);
			return ResponseEntity.of(Optional.of(customer));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
