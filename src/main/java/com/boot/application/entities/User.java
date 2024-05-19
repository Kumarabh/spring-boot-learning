package com.boot.application.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 50, nullable = false)
	private String firstName;
	
	@Column(length = 50, nullable = false)
	private String lastName;
	
	@Column(length = 50, nullable = false, unique = true)
	private String emailId;
	
	@Column(length = 50, nullable = false)
	private String password;
	
	@Column(length = 50, nullable = false)
	private String role;
	
	@Column
	private boolean enabled;
	
	@Column
	private String imageUrl;
	
	@Column
	private String about;
	
	
	
}
