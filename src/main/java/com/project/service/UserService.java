package com.project.service;

import java.util.List;

import com.project.models.entitys.User;

public interface UserService {

	public void create(User user);

	public void delete(Long id);

	public List<User> getAllUser();

	public User getById(Long id);

	public User modifyUser(User user, Long id);

	public User getByUserName(String userName);

	public boolean existsUserName(String userName);

	public boolean existsByEmail(String email);

}
