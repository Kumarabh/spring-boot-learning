package com.boot.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.application.entities.User;
import com.boot.application.services.UserService;

@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/")
	public ResponseEntity<List<User>> getAll() {
		List<User> users = null;
		try {
			users = this.service.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getById(@PathVariable("id") int id) {
		User user = null;
		try {
			boolean idExists = this.service.idExists(id);
			if(!idExists) {
				return new ResponseEntity<>("Invalid user id.", HttpStatus.BAD_REQUEST);
			}
			user = this.service.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<Object> create(@RequestBody User u) {
		User user = null;
		try {
			boolean emailExists = this.service.emailExists(u.getEmailId());
			if(emailExists) {
				return new ResponseEntity<>("Email id exists.", HttpStatus.BAD_REQUEST);
			}
			user = this.service.create(u);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@RequestBody User u, @PathVariable("id") int id) {
		User user = null;
		try {
			
			boolean idExists = this.service.idExists(id);
			if(!idExists) {
				return new ResponseEntity<>("Invalid user id.", HttpStatus.BAD_REQUEST);
			}
			boolean emailExists = this.service.emailExists(u.getEmailId());
			if(!emailExists) {
				return new ResponseEntity<>("Invalid email id.", HttpStatus.BAD_REQUEST);
			}
			user = this.service.update(u, id);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);		
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") int id) {
		try {
			boolean idExists = this.service.idExists(id);
			if(!idExists) {
				return new ResponseEntity<>("Invalid user id.", HttpStatus.BAD_REQUEST);
			}
			this.service.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			new ResponseEntity<>("Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Deleted Successfully.", HttpStatus.OK);
	}
	
}
