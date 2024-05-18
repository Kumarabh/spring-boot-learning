package com.boot.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.application.entities.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer>{
	
}
