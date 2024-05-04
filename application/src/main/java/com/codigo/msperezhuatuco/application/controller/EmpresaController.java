package com.codigo.msperezhuatuco.application.controller;

import com.codigo.msperezhuatuco.domain.aggregates.dto.EmpresaDTO;
import com.codigo.msperezhuatuco.domain.aggregates.request.EmpresaRequest;
import com.codigo.msperezhuatuco.domain.ports.in.EmpresaServiceIn;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/ms-perez-huatuco/v1/empresa")
@AllArgsConstructor
public class EmpresaController {

    private final EmpresaServiceIn empresaServiceIn;

    @PostMapping("/crear")
    public ResponseEntity<EmpresaDTO> registrar(@RequestBody EmpresaRequest empresaRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.crearEmpresaIn(empresaRequest));
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> buscarxId(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.buscarEmpresaxIdIn(id).get());
    }

    @GetMapping("/todos")
    public ResponseEntity<List<EmpresaDTO>> buscartodos(){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.buscarEmpresaTodosIn());
    }
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDTO> actualizar(@PathVariable Long id, @RequestBody EmpresaRequest empresaRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.actualizarEmpresaIn(id,empresaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmpresaDTO> delete(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.deleteEmpresaIn(id));
    }

}
