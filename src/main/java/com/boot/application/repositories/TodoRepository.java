package com.boot.application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.boot.application.entities.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer>{
	List<Todo> findByUserId(Integer id);
	
	// SEARCH
	List<Todo> findByTitleContaining(String title); 

	// SEARCH
	@Query("SELECT t FROM Todo t WHERE t.title LIKE %:keyword% OR t.description LIKE %:keyword%")
	List<Todo> searchTodos(@Param("keyword") String keyword);
}
