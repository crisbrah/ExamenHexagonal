package com.codigo.msperezhuatuco.domain.ports.in;

import com.codigo.msperezhuatuco.domain.aggregates.dto.PersonaDTO;
import com.codigo.msperezhuatuco.domain.aggregates.request.PersonaRequest;

import java.util.List;
import java.util.Optional;

public interface PersonaServiceIn {
    PersonaDTO crearPersonaIn(PersonaRequest personaRequest);
    Optional<PersonaDTO> buscarPersonaxIdIn(Long id);
    List<PersonaDTO> buscarPersonaTodosIn();
    PersonaDTO actualizarPersonaIn(Long id, PersonaRequest personaRequest);
    PersonaDTO deletePersonaIn(Long id);
}
