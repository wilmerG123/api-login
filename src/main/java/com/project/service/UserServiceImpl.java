package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.models.entitys.User;
import com.project.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void create(User user) {

		userRepository.save(user);
	}

	@Override
	public User modifyUser(User user, Long id) {

		return userRepository.save(user);

	}

	@Override
	public List<User> getAllUser() {

		return userRepository.findAll();
	}

	@Override
	public User getById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.get();
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);

	}

	@Override
	public User getByUserName(String userName) {
		User user = userRepository.findByUserName(userName).get();
		return user != null ? user : null;
	}

	@Override
	public boolean existsUserName(String userName) {

		return userRepository.existsByUserName(userName);
	}

	@Override
	public boolean existsByEmail(String email) {

		return userRepository.existsByemail(email);
	}

}
