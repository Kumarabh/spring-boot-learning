package com.boot.application.entities;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "note")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String title;
	
	@Column
	private String description;
	
	@Column
	private LocalDateTime createdDateTime;
	
	@Column
	private LocalDateTime updatedDateTime;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JsonBackReference
	User user;

	
}
