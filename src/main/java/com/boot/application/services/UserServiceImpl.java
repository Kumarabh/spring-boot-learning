package com.boot.application.services;

import java.util.List;

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
		User dbUser = this.repo.findById(id).get();
		dbUser.setFirstName(u.getFirstName());
		dbUser.setLastName(u.getLastName());
		return this.repo.save(dbUser);
	}

	@Override
	public void deleteById(int id) {
		this.repo.deleteById(id);

	}

	@Override
	public boolean emailExists(String email) {
		User user = this.repo.findByEmailId(email);
		return user != null;
	}

	public boolean idExists(int id) {
		return this.repo.existsById(id);
	}
	
}
