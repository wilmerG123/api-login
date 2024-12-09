package com.project.models.dtos;

import java.util.HashSet;
import java.util.Set;

public class NewUser {

	private String name;

	private String userName;

	private String email;

	private String password;
	private Set<String> roles = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setNombre(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setuserName(String nombreUsuario) {
		this.userName = nombreUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
}
