package com.boot.application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.application.entities.Todo;
import com.boot.application.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	public boolean existsUserByEmailId(String emailId);
//	List<Todo> findByFirstNameContaining(String title);

}
