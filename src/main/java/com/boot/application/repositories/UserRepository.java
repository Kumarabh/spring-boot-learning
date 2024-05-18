package com.boot.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.application.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByEmailId(String emailId);
}
