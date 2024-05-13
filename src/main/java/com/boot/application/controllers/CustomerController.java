package com.boot.application.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.application.entities.Customer;
import com.boot.application.services.CustomerService;

@RestController
@RequestMapping("/api/v1/customer/*")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@GetMapping("")
	public List<Customer> getAll() {
		return this.customerService.getAll();
	}
	
	@GetMapping("{customerId}")
	public Customer getById(@PathVariable("customerId") int id) {
		return this.customerService.getById(id);
	}
	
	@PostMapping("")
	public Customer create(@RequestBody Customer c) {
		return this.customerService.create(c);
	}
	
	@PutMapping("")
	public Customer update(@RequestBody Customer c) {
		return this.customerService.update(c);
	}
	
	@DeleteMapping("{customerId}")
	public ResponseEntity<String> delete(@PathVariable("customerId") int id) {
		this.customerService.deleteById(id);
		return ResponseEntity.of(Optional.of("Deleted Successfully."));
	}
}
