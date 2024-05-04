package com.codigo.msperezhuatuco.application.controller;

import com.codigo.msperezhuatuco.domain.aggregates.dto.PersonaDTO;
import com.codigo.msperezhuatuco.domain.aggregates.request.PersonaRequest;
import com.codigo.msperezhuatuco.domain.ports.in.PersonaServiceIn;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ms-perez-huatuco/v1/persona")
@AllArgsConstructor
public class PersonaController {
    private final PersonaServiceIn personaServiceIn;

    @PostMapping("/crear")
    public ResponseEntity<PersonaDTO> registrar(@RequestBody PersonaRequest requestPersona){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.crearPersonaIn(requestPersona));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> buscarxId(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.buscarPersonaxIdIn(id).get());
    }

    @GetMapping("/todos")
    public ResponseEntity<List<PersonaDTO>> buscartodos(){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.buscarPersonaTodosIn());
    }
    @PutMapping("/{id}")
    public ResponseEntity<PersonaDTO> actualizar(@PathVariable Long id, @RequestBody PersonaRequest personaRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.actualizarPersonaIn(id,personaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonaDTO> delete(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.deletePersonaIn(id));
    }


}
