package com.boot.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.application.entities.User;
import com.boot.application.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository repo;
	
	@Override
	public List<User> getAll() {
		return this.repo.findAll();
	}

	@Override
	public User getById(int id) {
		return this.repo.findById(id).get();
	}

	@Override
	public User create(User u) {
		return this.repo.save(u);
	}

	@Override
	public User update(User u, int id) {
		Optional<User> dbuser = this.repo.findById(id);
		User updatedUser = null;
		if(dbuser.isPresent()) {
			updatedUser = dbuser.get();
			updatedUser.setFirstName(u.getFirstName());
			updatedUser.setLastName(u.getLastName());
			updatedUser.setTodos(u.getTodos());
			updatedUser = this.repo.save(updatedUser);
		}
		return updatedUser;
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
	public boolean emailExists(String emailId) {		
		return this.repo.existsUserByEmailId(emailId);
	}
}
