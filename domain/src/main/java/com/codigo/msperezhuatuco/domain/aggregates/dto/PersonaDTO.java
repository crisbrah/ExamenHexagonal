package com.codigo.msperezhuatuco.domain.aggregates.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class PersonaDTO {
    private Long idPersona;
    private String numDocu;
    private String nombres;
    private String apePat;
    private String apeMat;
    private Integer estado;
    private String usuaCrea;
    private Timestamp dateCreate;
    private String usuaModif;
    private Timestamp dateModif;
    private String usuaDelet;
    private Timestamp dateDelet;
}
