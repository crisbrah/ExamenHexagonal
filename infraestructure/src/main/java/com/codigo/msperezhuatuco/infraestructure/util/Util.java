package com.codigo.msperezhuatuco.infraestructure.util;

import com.codigo.msperezhuatuco.domain.aggregates.dto.EmpresaDTO;
import com.codigo.msperezhuatuco.domain.aggregates.dto.PersonaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {
    public static String convertirAStringPersona(PersonaDTO personaDto){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(personaDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T convertirDesdeString( String json, Class<T> tipoClase){
        try {
            ObjectMapper objectMapper= new ObjectMapper();
            return objectMapper.readValue(json,tipoClase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertirAStringEmpresa(EmpresaDTO empresaDTO){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(empresaDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
