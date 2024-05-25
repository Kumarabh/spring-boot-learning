package com.boot.application.services;

import java.util.List;
import java.util.Map;

import com.boot.application.entities.Todo;

public interface TodoService {

	public Map<String, Object> getAll(int pageNumber, int pageSize, String sortBy, String sortDirection);
	public Todo getById(int id);
	public Todo create(Todo t);
	public Todo update(Todo t, int id);
	public void deleteById(int id);
	public boolean idExists(int id);
	public List<Todo> getByUserId(int id);
	public List<Todo> searchTodo(String keyword);

}
