package com.boot.application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.boot.application.dao.CustomerRepository;
import com.boot.application.entities.Customer;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
	    ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
	    
	    CustomerRepository customerRepo = applicationContext.getBean(CustomerRepository.class);
	    
	    // ----- Create customer
//	    Customer c1 = new Customer("John Doe", LocalDate.parse("2000-05-01"), "Male");
//	    Customer c2 = new Customer("James Smith", LocalDate.parse("1995-05-01"), "Male");
//	    Customer c3 = new Customer("Jessica Jones", LocalDate.parse("1995-05-01"), "Female");
//	    Customer c4 = new Customer("Jack Ryan", LocalDate.parse("1995-05-01"), "Male");
//	    Customer c5 = new Customer("Elenor Shellstrop", LocalDate.parse("1995-05-01"), "Female");
//
//	    List<Customer> customerList = new ArrayList<>(Arrays.asList(c1, c2, c3, c4, c5));
//	    
	    
//	    // ----- Save single customer
//	    Customer savedCustomerResult = customerRepo.save(c1);
//	    System.out.println(savedCustomerResult);
	    
	    
	    // ----- Save multiple customer
//	    List<Customer> savedCustomerList = customerRepo.saveAll(customerList);
//	    savedCustomerList.forEach(e -> {
//	    	System.out.println(e.getCustomerName());
//	    });
	    
	    
	    // ----- Get all customer
	    List<Customer> customerListResultData = customerRepo.findAll();
	    customerListResultData.forEach((customer) -> {
	    	System.out.println(customer.getCustomerName() + " | " + customer.getDateOfBirth());
	    });
	    
	    
	    // ---- Update customer
	    Customer firstCustomer = customerListResultData.get(0);
	    firstCustomer.setCustomerName("John Constantino");
	    customerRepo.save(firstCustomer);
	    
	    // ---- Get a customer
	    Optional<Customer> customerResult = customerRepo.findById(firstCustomer.getCustomerId());
	    System.out.println("==> After updated"+ customerResult.get().getCustomerName());
	    
	}

}
