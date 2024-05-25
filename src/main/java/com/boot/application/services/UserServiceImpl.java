package com.boot.application.services;

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

import com.boot.application.entities.User;
import com.boot.application.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository repo;
	
	@Override
	public Map<String, Object> getAll(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
		Pageable pageable = null;
		if(sortDirection.equalsIgnoreCase("asc")) {
			pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
		} else {
			pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());

		}
		Page<User> pageUser = this.repo.findAll(pageable);
		System.out.println(pageUser.getContent());

		Map<String, Object> response = new HashMap<>();
		response.put("currentPage", pageUser.getNumber());
		response.put("totalItems", pageUser.getTotalElements());
		response.put("totalPages", pageUser.getTotalPages());
		response.put("data", pageUser.getContent());
		return response;
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

	@Override
	public List<User> getAll() {
		return this.repo.findAll();
	}
}
