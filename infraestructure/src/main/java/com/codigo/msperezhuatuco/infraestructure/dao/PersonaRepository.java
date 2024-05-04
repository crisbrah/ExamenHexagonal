package com.codigo.msperezhuatuco.infraestructure.dao;

import com.codigo.msperezhuatuco.infraestructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
}
