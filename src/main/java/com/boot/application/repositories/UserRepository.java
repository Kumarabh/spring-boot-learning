package com.boot.application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.boot.application.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByEmailId(String emailId);
	
	@Query("SELECT u from User u where u.firstName LIKE :key OR u.lastName LIKE :key")
	public List<User> searchByName(@Param("key") String keyword);
}
