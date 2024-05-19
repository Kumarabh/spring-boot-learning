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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boot.application.entities.Note;
import com.boot.application.entities.User;
import com.boot.application.models.NoteResponse;
import com.boot.application.services.NoteService;

@RestController
@RequestMapping("/api/v1/note")
public class NoteController {

	@Autowired
	private NoteService service;
	
	@GetMapping("")
	public ResponseEntity<Object> getAll(
		@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
		@RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
		@RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
		@RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection
		) {
		NoteResponse notes = null;
		try {
			notes = this.service.getAll(pageNumber, pageSize, sortBy, sortDirection);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(notes, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getById(@PathVariable("id") int id) {
		Note note = null;
		
		try {
			boolean idExists = this.service.idExists(id);
			if(!idExists) return new ResponseEntity<>("Note not found.", HttpStatus.BAD_REQUEST);
			
			note = this.service.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(note, HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<Object> create(@RequestBody Note n) {
		Note note = null;
		
		try {
			boolean idExists = this.service.idExists(n.getId());
			if (idExists) return new ResponseEntity<>("Note exists.", HttpStatus.BAD_REQUEST);		
			User user = n.getUser();
			note = this.service.create(n);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(note, HttpStatus.OK);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@RequestBody Note n, @PathVariable("id") int id) {
		Note note = null;
		
		try {
			boolean idExists = this.service.idExists(id);
			if(id == 0 ) return new ResponseEntity<>("Id is required.", HttpStatus.BAD_REQUEST);
			if(!idExists ) return new ResponseEntity<>("Invalid note id.", HttpStatus.BAD_REQUEST);
			
			note = this.service.update(n, id);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(note, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable int id) {
		try {
			boolean idExists = this.service.idExists(id);
			if(!idExists) return new ResponseEntity<>("Invalid note id.", HttpStatus.BAD_REQUEST);
			this.service.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Deleted Successfully.", HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public ResponseEntity<Object> getByUser(@RequestBody User u) {
		List<Note> notes = null;
		try {
			notes = this.service.getByUser(u);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(notes, HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<Object> getByUserId(@PathVariable int id) {
		List<Note> notes = null;
		try {
			notes = this.service.getByUserId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(notes, HttpStatus.OK);
	}
	
	@GetMapping("/search/{keyword}")
	public ResponseEntity<Object> searchByNoteTitle(@PathVariable("keyword") String keyword) {
		List<Note> notes = null;
		try {
			notes = this.service.searchNote(keyword);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(notes, HttpStatus.OK);
	}
}
