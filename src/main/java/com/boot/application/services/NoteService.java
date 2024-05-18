package com.boot.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boot.application.entities.Note;

@Service
public interface NoteService {

	public List<Note> getAll();
	public Note getById(int id);
	public Note create(Note n);
	public Note update(Note n, int id);
	public void deleteById(int id);
	public boolean idExists(int id);
}
