package com.codigo.msperezhuatuco.domain.ports.out;

import com.codigo.msperezhuatuco.domain.aggregates.dto.PersonaDTO;
import com.codigo.msperezhuatuco.domain.aggregates.request.PersonaRequest;

import java.util.List;
import java.util.Optional;

public interface PersonaServiceOut {
    PersonaDTO crearPersonaOut(PersonaRequest personaRequest);
    Optional<PersonaDTO> buscarPersonaxIdOut(Long id);
    List<PersonaDTO> buscarPersonaTodosOut();
    PersonaDTO actualizarPersonaOut(Long id, PersonaRequest personaRequest);
    PersonaDTO deletePersonaOut(Long id);
}
