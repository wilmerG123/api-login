package com.project.models.entitys;

import com.project.models.enums.RolEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rol")
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private RolEnum rolName;

	public Rol() {

	}

	public Rol(RolEnum rolNombre) {

		this.rolName = rolNombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RolEnum getRolNombre() {
		return rolName;
	}

	public void setRolNombre(RolEnum rolNombre) {
		this.rolName = rolNombre;
	}

}
