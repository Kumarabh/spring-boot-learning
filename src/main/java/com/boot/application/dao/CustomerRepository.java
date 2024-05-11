package com.boot.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.application.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
