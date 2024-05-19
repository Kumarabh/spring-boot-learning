package com.boot.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.application.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByEmailId(String emailId);

}
