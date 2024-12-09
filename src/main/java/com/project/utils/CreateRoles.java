package com.project.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.project.service.RolServiceImpl;

/**
 * MUY IMPORTANTE: ESTA CLASE SÓLO SE EJECUTARÁ UNA VEZ PARA CREAR LOS ROLES.
 * UNA VEZ CREADOS SE DEBERÁ ELIMINAR O BIEN COMENTAR EL CÓDIGO
 *
 */

@Component
public class CreateRoles implements CommandLineRunner {

	@Autowired
	RolServiceImpl rolServiceImpl;

	@Override
	public void run(String... args) throws Exception {
		/*
		 * Rol rolAdmin = new Rol(RolEnum.ROLE_ADMIN); Rol rolUser = new
		 * Rol(RolEnum.ROLE_USER); rolServiceImpl.create(rolAdmin);
		 * rolServiceImpl.create(rolUser);
		 */

	}
}
