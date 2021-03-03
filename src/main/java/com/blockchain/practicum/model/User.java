package com.blockchain.practicum.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
/**
 *user class holds user data 
 *
 */
public class User {
	@NotBlank(message = "Name cannot be missing or empty")
	@Size(min = 2, message = "Name must not be less than 2 characters")
	private String name;
	@NotBlank(message = "Password is a required field")
	@Size(min = 8, max = 16, message = "Password must be equal to or greater than 8 characters and less than 16")
	private String password;
	@NotBlank
	@Email(message = "a valid email is mandatory")
	private String email;
	@JsonProperty(access = Access.READ_ONLY)
	private String id;
	private float VC;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getVC() {
		return VC;
	}

	public void setVC(float VC) {
		this.VC = VC;
	}

}
