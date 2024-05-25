package com.boot.application.services;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.boot.application.entities.Todo;
import com.boot.application.entities.User;
import com.boot.application.repositories.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository repo;

	@Override
	public Todo getById(int id) {
		return this.repo.findById(id).get();
	}

	@Override
	public Todo create(Todo t) {
		t.setCreatedAt(LocalDateTime.now());
		return this.repo.save(t);
	}

	@Override
	public Todo update(Todo t, int id) {
		Optional<Todo> dbTodo = this.repo.findById(id);
		Todo updatedTodo = null;
		if(dbTodo.isPresent()) {
			updatedTodo.setTitle(t.getTitle());
			updatedTodo.setDescription(t.getDescription());
			updatedTodo.setUpdatedAt(LocalDateTime.now());
			updatedTodo.setUser(t.getUser());
			updatedTodo = this.repo.save(updatedTodo);
		}
		return updatedTodo;
	}

	@Override
	public void deleteById(int id) {
		this.repo.deleteById(id);
		
	}

	@Override
	public boolean idExists(int id) {
		return this.repo.existsById(id);
		
	}

	@Override
	public List<Todo> getByUserId(int id) {
		return this.repo.findByUserId(id);
	}

	@Override
	public Map<String, Object> getAll(int pageNumber, int pageSize, String sortBy, String sortDirection) {
		Pageable pageable = null;
		if(sortDirection.equalsIgnoreCase("asc")) {
			pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
		} else {
			pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
		}
		
		Page<Todo> pageTodo = this.repo.findAll(pageable);
		
		Map<String, Object> response = new HashMap<>();
		response.put("currentPage", pageTodo.getNumber());
		response.put("totalItems", pageTodo.getTotalElements());
		response.put("totalPages", pageTodo.getTotalPages());
		response.put("data", pageTodo.getContent());
		
		return response;
	}

	@Override
	public List<Todo> searchTodo(String keyword) {
		return this.repo.searchTodos(keyword);
	}

	
}
