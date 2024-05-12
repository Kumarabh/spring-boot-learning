// On load of a class during it's object creation, 
// First static properties are created
// Then static blocks are executed

package com.boot.application.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.boot.application.entities.Customer;

@Service
public class CustomerService {

	private static List<Customer> customerList = new ArrayList<>();
	
	static { 
		
		Customer c1 = new Customer(101, "John Doe", LocalDate.parse("1994-01-01"), "Male");
		Customer c2 = new Customer(102, "James Smith", LocalDate.parse("1994-01-02"), "Male");
		Customer c3 = new Customer(103, "John Constantine", LocalDate.parse("1994-01-03"), "Male");
		Customer c4 = new Customer(104, "Gustavo Gaviria", LocalDate.parse("1994-01-04"), "Male");
		Customer c5 = new Customer(105, "Elenor Shellstrop", LocalDate.parse("1998-01-03"), "Female");

		CustomerService.customerList.addAll(Arrays.asList(c1, c2, c3, c4, c5));
		
	}
	
	public List<Customer> getAllCustomers() {
		return this.customerList;
	}
	
	public Customer getCustomerById(int customerId) {
		return this.customerList.stream().filter((e) -> e.getCustomerId() == customerId).findFirst().get();
	}
	
	public Customer createCustomer(Customer customer) {
		this.customerList.add(customer);
		return customer;
	}
}
