package com.codigo.msperezhuatuco.infraestructure.dao;

import com.codigo.msperezhuatuco.infraestructure.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {
}
