package com.boot.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	   
	   // ---- find all
	   
	   List<Customer> dbCustomerList = customerRepo.findAll();
	   
	   dbCustomerList.forEach((e) -> { 
		   System.out.println(e.getCustomerName());
	   });
	   
	   System.out.println("++++");
	   
	   // ---- find by gender
	   
	   List<Customer> dbCustomerList2 = customerRepo.findByGender("Male");
	   dbCustomerList2.forEach((e) -> { 
		   System.out.println("Name: " + e.getCustomerName() + " Gender: " + e.getGender());
	   });
	   
	   System.out.println("++++");

       // ---- find by name and gender
	   
	   List<Customer> dbCustomerList3 = customerRepo.findByCustomerNameAndGender("James Smith", "Male");
	   dbCustomerList3.forEach((e) -> { 
		   System.out.println("Name: " + e.getCustomerName() + " Gender: " + e.getGender());
	   });
	   
	   
	   System.out.println("++++");

       // ---- find by name starting with
	   
	   List<Customer> dbCustomerList4 = customerRepo.findByCustomerNameStartingWith("J");
	   dbCustomerList4.forEach((e) -> { 
		   System.out.println("Name: " + e.getCustomerName() + " Gender: " + e.getDateOfBirth());
	   });
	   
	   System.out.println("++++");

       // ---- find by name in
	   List<String> customerNamesList = new ArrayList<>(Arrays.asList("James Smith", "Elenor Shellstrop"));
	   List<Customer> dbCustomerList5 = customerRepo.findByCustomerNameIn(customerNamesList);
	   dbCustomerList5.forEach((e) -> { 
		   System.out.println("Name: " + e.getCustomerName() + " Gender: " + e.getDateOfBirth());
	   });
	   
	}

}
