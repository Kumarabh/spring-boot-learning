package com.boot.application.services;

import java.util.List;

import com.boot.application.entities.Todo;

public interface TodoService {

	public List<Todo> getAll();
	public Todo getById(int id);
	public Todo create(Todo t);
	public Todo update(Todo t, int id);
	public void deleteById(int id);
	public boolean idExists(int id);

}
