package com.project.service;

import com.project.models.entitys.Rol;
import com.project.models.enums.RolEnum;


public interface RolService {

	public Rol create(Rol rol);

	public void delete(Long id);
	
	public Rol getByRolName(RolEnum rolName);
}
