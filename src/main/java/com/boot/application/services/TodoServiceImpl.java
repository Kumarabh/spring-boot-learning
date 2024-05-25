package com.boot.application.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.application.entities.Todo;
import com.boot.application.repositories.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository repo;
	
	@Override
	public List<Todo> getAll() {
		return this.repo.findAll();
	}

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
	
}
