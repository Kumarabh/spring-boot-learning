// On load of a class during it's object creation, 
// First static properties are created
// Then static blocks are executed

package com.boot.application.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
		Customer customer = null;
		try {
			customer = this.customerList.stream().filter((e) -> e.getCustomerId() == customerId).findFirst().get();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		return customer;
	}
	
	public Customer createCustomer(Customer customer) {
		this.customerList.add(customer);
		return customer;
	}
	
	public void deleteCustomer(int customerId) {
		this.customerList = this.customerList.stream().filter(e -> e.getCustomerId() != customerId ).collect(Collectors.toList());
		
	}
	
	public Customer updateCustomer(Customer customer) {
		this.customerList = this.customerList.stream().map(e -> {
			if(e.getCustomerId() == customer.getCustomerId()) {
				return customer;
			}
			return e;
		}).collect(Collectors.toList());
		
		Customer c1 =  this.customerList.stream().filter(e -> e.getCustomerId() == customer.getCustomerId()).findFirst().get();
		System.out.println(c1.toString());
		return c1;
		
	}
}
