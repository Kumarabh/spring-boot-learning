package com.boot.application.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.boot.application.entities.Note;
import com.boot.application.entities.User;
import com.boot.application.models.NoteResponse;
import com.boot.application.repositories.NoteRepository;

@Service
public class NoteServiceImpl implements NoteService{

	@Autowired
	private NoteRepository repo;
	
	@Override
	public NoteResponse getAll(int pageNumber, int pageSize, String sortBy, String sortDirection) {
		Pageable pageable = null;
		if(sortDirection.equalsIgnoreCase("asc")) {
			pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
		} else if(sortDirection.equalsIgnoreCase("desc")){
			pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
		} else {
			pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
		}
			
		Page<Note> page= this.repo.findAll(pageable);
		NoteResponse noteResponse = new NoteResponse(page.getContent(), page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages(), page.isLast());
		
		return noteResponse;
	}

	@Override
	public Note getById(int id) {
		return this.repo.findById(id).get();
	}

	@Override
	public Note create(Note n) {
		n.setCreatedDateTime(LocalDateTime.now());
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
		note.setUpdatedDateTime(LocalDateTime.now());
		
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

	@Override
	public List<Note> getByUser(User u) {
		return this.repo.findByUser(u);
	}

	@Override
	public List<Note> getByTitle(String title) {
		return this.repo.findByTitleContaining(title);
	}

	@Override
	public List<Note> getByUserId(int id) {
		return this.repo.findByUserId(id);
	}
	
	@Override
	public List<Note> searchNote(String keyword) {
		return this.repo.findByTitleContaining(keyword);
	}
}
