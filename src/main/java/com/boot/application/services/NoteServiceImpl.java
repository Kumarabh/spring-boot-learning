package com.boot.application.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.application.entities.Note;
import com.boot.application.repositories.NoteRepository;

@Service
public class NoteServiceImpl implements NoteService{

	@Autowired
	private NoteRepository repo;
	
	@Override
	public List<Note> getAll() {
		return this.repo.findAll();
	}

	@Override
	public Note getById(int id) {
		return this.repo.findById(id).get();
	}

	@Override
	public Note create(Note n) {
		n.setCreationDateTime(LocalDateTime.now());
		return this.repo.save(n);
		
	}

	@Override
	public Note update(Note n, int id) {
		// TODO Auto-generated method stub
		Note note = null;
		note = this.repo.findById(id).get();
		note.setTitle(n.getTitle());
		note.setDescription(n.getDescription());
		note.setUser(n.getUser());
		
		return this.repo.save(note);
	}

	@Override
	public void deleteById(int id) {
		this.repo.deleteById(id);
	}

	@Override
	public boolean idExists(int id) {
		return this.repo.existsById(id);
	}
	
}
