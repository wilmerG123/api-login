package com.project.models.entitys;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserMain implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String userName;
	private String password;
	private String email;
	private Collection<? extends GrantedAuthority> authorities;

	public static UserMain build(User user) {

		List<GrantedAuthority> autorities = user.getRoles().stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(Collectors.toList());
		return new UserMain(user.getName(), user.getUserName(), user.getPassword(), user.getEmail(), autorities);
	}

	public UserMain(String name, String userName, String password, String email,
			Collection<? extends GrantedAuthority> authorities) {

		this.name = name;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getNombre() {
		return name;
	}

	public String getEmail() {
		return email;
	}

}
