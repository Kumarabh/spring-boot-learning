package com.boot.application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.application.entities.Note;
import com.boot.application.entities.User;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer>{
	
	List<Note> findByUser(User user);
	List<Note> findByUserId(int id);
	List<Note> findByTitleContaining(String title);

}
