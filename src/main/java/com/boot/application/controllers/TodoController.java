package com.boot.application.controllers;

import java.time.LocalDateTime;
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

import com.boot.application.entities.Todo;
import com.boot.application.services.TodoService;
import com.boot.application.services.UserService;

@RestController
@RequestMapping(path = "/api/v1/todo")
public class TodoController {

	@Autowired
	private TodoService service;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public ResponseEntity<List<Todo>> getAll() {
		List<Todo> todos = null;
		try {
			todos = this.service.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<>(todos, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getById(@PathVariable("id") int id) {
		Todo todo = null;
		try {
			boolean idExists = this.service.idExists(id);
			if(!idExists) {
				return new ResponseEntity<>("Invalid todo id.", HttpStatus.BAD_REQUEST);
			}
			todo = this.service.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<>(todo, HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<Object> create(@RequestBody Todo t) {
		Todo todo = null;

		if(t.getUser().getId() == 0) {
			return new ResponseEntity<>("Invalid USER ID.", HttpStatus.BAD_REQUEST);
		}
		if(t.getUser().getEmailId() == "") {
			return new ResponseEntity<>("Invalid EMAIL ID.", HttpStatus.BAD_REQUEST);
		}
		
		try {
			boolean userExists = this.userService.emailExists(t.getUser().getEmailId());
			if(!userExists) {
				return new ResponseEntity<>("Invalid User.", HttpStatus.BAD_REQUEST);
			}
			t.setCreatedAt(LocalDateTime.now());
			todo = this.service.create(t);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(todo, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@RequestBody Todo t, @PathVariable("id") int id) {
		Todo todo = null;
		try {
			
			boolean idExists = this.service.idExists(id);
			if(!idExists) {
				return new ResponseEntity<>("Invalid todo id.", HttpStatus.BAD_REQUEST);
			}
			boolean userExists = this.userService.emailExists(t.getUser().getEmailId());
			if(!userExists) {
				return new ResponseEntity<>("Invalid User.", HttpStatus.BAD_REQUEST);
			}
			t.setUpdatedAt(LocalDateTime.now());
			todo = this.service.update(t, id);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);		
		}
		return new ResponseEntity<>(todo, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") int id) {
		try {
			boolean idExists = this.service.idExists(id);
			if(!idExists) {
				return new ResponseEntity<>("Invalid todo id.", HttpStatus.BAD_REQUEST);
			}
			this.service.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			new ResponseEntity<>("Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Deleted Successfully.", HttpStatus.OK);
	}
	
}