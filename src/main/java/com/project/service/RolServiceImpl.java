package com.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.models.entitys.Rol;
import com.project.models.enums.RolEnum;
import com.project.repository.RolRepository;

@Service
public class RolServiceImpl implements RolService {
	
	@Autowired
	RolRepository rolRepository;

	@Override
	public Rol create(Rol rol) {
		return rolRepository.save(rol);
	}

	@Override
	public void delete(Long id) {
		rolRepository.deleteById(id);
	}

	@Override
	public Rol getByRolName(RolEnum rolName) {

		Optional<Rol> rol = rolRepository.findByRolName(rolName);
		return rol.get();
	}

}
