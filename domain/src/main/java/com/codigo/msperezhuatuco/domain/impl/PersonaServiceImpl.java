package com.codigo.msperezhuatuco.domain.impl;

import com.codigo.msperezhuatuco.domain.aggregates.dto.PersonaDTO;
import com.codigo.msperezhuatuco.domain.aggregates.request.PersonaRequest;
import com.codigo.msperezhuatuco.domain.ports.in.PersonaServiceIn;
import com.codigo.msperezhuatuco.domain.ports.out.PersonaServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonaServiceImpl implements PersonaServiceIn {
    private final PersonaServiceOut personaServiceOut;
    @Override
    public PersonaDTO crearPersonaIn(PersonaRequest personaRequest) {
        return personaServiceOut.crearPersonaOut(personaRequest);
    }

    @Override
    public Optional<PersonaDTO> buscarPersonaxIdIn(Long id) {
        return personaServiceOut.buscarPersonaxIdOut(id);
    }

    @Override
    public List<PersonaDTO> buscarPersonaTodosIn() {
        return personaServiceOut.buscarPersonaTodosOut();
    }

    @Override
    public PersonaDTO actualizarPersonaIn(Long id, PersonaRequest personaRequest) {
        return personaServiceOut.actualizarPersonaOut(id, personaRequest);
    }

    @Override
    public PersonaDTO deletePersonaIn(Long id) {
        return personaServiceOut.deletePersonaOut(id);
    }
}
