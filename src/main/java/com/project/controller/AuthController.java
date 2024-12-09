package com.project.controller;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.jwt.JwtProvider;
import com.project.models.dtos.JwtDto;
import com.project.models.dtos.LoginUser;
import com.project.models.dtos.NewUser;
import com.project.models.entitys.Rol;
import com.project.models.entitys.User;
import com.project.models.enums.RolEnum;
import com.project.service.RolServiceImpl;
import com.project.service.UserServiceImpl;
import com.project.utils.Mensaje;

@RestController
@RequestMapping("/auth/")
@CrossOrigin
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private RolServiceImpl rolServiceImpl;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/create-user")
	public ResponseEntity<?> nuevo(@RequestBody NewUser newUser, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity<>(new Mensaje("Campos mal puestos o formato email erroneo"),
					HttpStatus.BAD_REQUEST);
		if (userServiceImpl.existsUserName(newUser.getUserName()))
			return new ResponseEntity<>(new Mensaje("Username ya existe"), HttpStatus.BAD_REQUEST);
		if (userServiceImpl.existsByEmail(newUser.getEmail()))
			return new ResponseEntity<>(new Mensaje("Email ya existe"), HttpStatus.BAD_REQUEST);
		User user = new User(newUser.getName(), newUser.getUserName(), newUser.getEmail(),
				passwordEncoder.encode(newUser.getPassword()));
		Set<Rol> roles = new HashSet<>();
		if (newUser.getRoles().contains("admin")){
			roles.add(rolServiceImpl.getByRolName(RolEnum.ROLE_ADMIN));
		} else {
			roles.add(rolServiceImpl.getByRolName(RolEnum.ROLE_USER));
		}
		user.setRoles(roles);
		userServiceImpl.create(user);
		return new ResponseEntity<>(new Mensaje("Usuario Guardado Exitosamente"), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@RequestBody LoginUser loginUser, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new Mensaje("Campos Mal Colocados"), HttpStatus.BAD_REQUEST);
		}
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity<JwtDto>(jwtDto, HttpStatus.OK);
	}

	@PostMapping("/refresh")
	public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) throws ParseException {
		String token = jwtProvider.refreshToken(jwtDto);

		JwtDto jwt = new JwtDto(token);
		return new ResponseEntity<JwtDto>(jwt, HttpStatus.OK);
	}

	@GetMapping("/get-users")
	public List<User> getAllUsers() {
		return userServiceImpl.getAllUser();
	}

	@DeleteMapping("/{id}")
	public void delteUser(@PathVariable Long id) {
		userServiceImpl.delete(id);
	}

}
