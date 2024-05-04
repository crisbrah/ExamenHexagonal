package com.codigo.msperezhuatuco.domain.ports.in;

import com.codigo.msperezhuatuco.domain.aggregates.dto.EmpresaDTO;
import com.codigo.msperezhuatuco.domain.aggregates.dto.PersonaDTO;
import com.codigo.msperezhuatuco.domain.aggregates.request.EmpresaRequest;
import com.codigo.msperezhuatuco.domain.aggregates.request.PersonaRequest;

import java.util.List;
import java.util.Optional;

public interface EmpresaServiceIn {
    EmpresaDTO crearEmpresaIn(EmpresaRequest empresaRequest);
    Optional<EmpresaDTO> buscarEmpresaxIdIn(Long id);
    List<EmpresaDTO> buscarEmpresaTodosIn();
    EmpresaDTO actualizarEmpresaIn(Long id, EmpresaRequest empresaRequest);
    EmpresaDTO deleteEmpresaIn(Long id);
}
