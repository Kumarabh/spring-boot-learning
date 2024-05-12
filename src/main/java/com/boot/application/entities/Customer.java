package com.boot.application.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerId;
	
	@Column
	private String customerName;
	
	@Column
	private LocalDate dateOfBirth;

	@Column
	private String gender;
	
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(int customerId, String customerName, String gender, LocalDate dateOfBirth) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
	}

	public Customer(String customerName, LocalDate dateOfBirth, String gender) {
		super();
		this.customerName = customerName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
	}
	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
}
