package com.boot.application.services;

import java.util.List;
import java.util.Map;

import com.boot.application.entities.User;

public interface UserService {

//	public List<User> getAll();
	public User getById(int id);
	public User create(User u);
	public User update(User u, int id);
	public void deleteById(int id);
	public boolean idExists(int id);
	public boolean emailExists(String email);
	public Map<String, Object> getAll(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);
	public List<User> getAll();
}
