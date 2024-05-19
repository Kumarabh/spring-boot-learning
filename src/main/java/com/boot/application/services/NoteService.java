package com.boot.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boot.application.entities.Note;
import com.boot.application.entities.User;
import com.boot.application.models.NoteResponse;

@Service
public interface NoteService {

	public NoteResponse getAll(int pageNumber, int pageSize, String sortBy, String sortDirection);
	public Note getById(int id);
	public Note create(Note n);
	public Note update(Note n, int id);
	public void deleteById(int id);
	public boolean idExists(int id);

	public List<Note> getByUser(User u);
	public List<Note> getByTitle(String title);
	public List<Note> getByUserId(int id);
	public List<Note> searchNote(String keyword);
}
