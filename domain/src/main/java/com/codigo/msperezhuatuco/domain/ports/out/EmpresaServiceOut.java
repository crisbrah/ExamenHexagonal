package com.codigo.msperezhuatuco.domain.ports.out;

import com.codigo.msperezhuatuco.domain.aggregates.dto.EmpresaDTO;
import com.codigo.msperezhuatuco.domain.aggregates.request.EmpresaRequest;

import java.util.List;
import java.util.Optional;

public interface EmpresaServiceOut {
    EmpresaDTO crearEmpresaOut(EmpresaRequest empresaRequest);
    Optional<EmpresaDTO> buscarEmpresaxIdOut(Long id);
    List<EmpresaDTO> buscarEmpresaTodosOut();
    EmpresaDTO actualizarEmpresaOut(Long id, EmpresaRequest empresaRequest);
    EmpresaDTO deleteEmpresaOut(Long id);
}
