package com.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.models.entitys.Rol;
import com.project.models.enums.RolEnum;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

	Optional<Rol> findByRolName(RolEnum rol);

}
