package com.boot.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.boot.application.dao.CustomerRepository;
import com.boot.application.entities.Customer;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
	    ApplicationContext context = SpringApplication.run(Application.class, args);
	    
	    CustomerRepository customerRepo = context.getBean(CustomerRepository.class);
	    
	    Customer c1 = new Customer();
	    c1.setCustomerName("John Doe");
	    
	    Customer result = customerRepo.save(c1);
	    System.out.println(result.getCustomerName());
	}

}
