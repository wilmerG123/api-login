package com.project.models.dtos;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtDto {

	private String token;
	private String bearer = "Bearer";
	private String userName;
	private Collection<? extends GrantedAuthority> authorities;

	public JwtDto() {
	}

	public JwtDto(String token) {
		this.token = token;
	}

	public JwtDto(String token, String userName, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.token = token;
		this.userName = userName;
		this.authorities = authorities;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBearer() {
		return bearer;
	}

	public void setBearer(String bearer) {
		this.bearer = bearer;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

}
