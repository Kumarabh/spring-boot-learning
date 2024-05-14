package com.boot.application.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
	public ResponseEntity<List<Customer>> getAll() {

		List<Customer> customers = null;

		try {
			customers = this.customerService.getAll();
			if(customers.size() <= 0) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.ok().body(customers);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("{customerId}")
	public ResponseEntity<Customer> getById(@PathVariable("customerId") int id) {

		try {
			Customer customer = this.customerService.getById(id);
			if (customer == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.status(HttpStatus.OK).body(customer);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("")
	public ResponseEntity<Customer> create(@RequestBody Customer c) {
		try {
			Customer customer = this.customerService.create(c);
			
			if(customer == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(customer);
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@PutMapping("{customerId}")
	public ResponseEntity<Customer> update(@RequestBody Customer c, @PathVariable("customerId") int id) {
		Customer customer = null;
		try {
			customer = this.customerService.update(c, id);
			
			if(customer == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(customer);
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@DeleteMapping("{customerId}")
	public ResponseEntity<String> delete(@PathVariable("customerId") int id) {
		
		try {
			this.customerService.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
