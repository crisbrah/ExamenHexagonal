package com.codigo.msperezhuatuco.domain.impl;

import com.codigo.msperezhuatuco.domain.aggregates.dto.EmpresaDTO;
import com.codigo.msperezhuatuco.domain.aggregates.request.EmpresaRequest;
import com.codigo.msperezhuatuco.domain.aggregates.request.PersonaRequest;
import com.codigo.msperezhuatuco.domain.ports.in.EmpresaServiceIn;
import com.codigo.msperezhuatuco.domain.ports.out.EmpresaServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmpresaServiceImpl implements EmpresaServiceIn {
    private final EmpresaServiceOut empresaServiceOut;
    @Override
    public EmpresaDTO crearEmpresaIn(EmpresaRequest empresaRequest) {
        return empresaServiceOut.crearEmpresaOut(empresaRequest);
    }

    @Override
    public Optional<EmpresaDTO> buscarEmpresaxIdIn(Long id) {
        return empresaServiceOut.buscarEmpresaxIdOut(id);
    }

    @Override
    public List<EmpresaDTO> buscarEmpresaTodosIn() {
        return empresaServiceOut.buscarEmpresaTodosOut();
    }

    @Override
    public EmpresaDTO actualizarEmpresaIn(Long id, EmpresaRequest empresaRequest) {
        return empresaServiceOut.actualizarEmpresaOut(id, empresaRequest);
    }

    @Override
    public EmpresaDTO deleteEmpresaIn(Long id) {
        return empresaServiceOut.deleteEmpresaOut(id);
    }
}
