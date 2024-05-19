package com.boot.application.services;

import java.util.List;

import com.boot.application.entities.User;

public interface UserService {

	public List<User> getAll();
	public User getById(int id);
	public User create(User u);
	public User update(User u, int id);
	public void deleteById(int id);
	public boolean emailExists(String email);
	public boolean idExists(int id);
}
