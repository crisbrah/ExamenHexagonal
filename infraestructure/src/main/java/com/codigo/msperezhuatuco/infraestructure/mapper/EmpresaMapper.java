package com.codigo.msperezhuatuco.infraestructure.mapper;

import com.codigo.msperezhuatuco.domain.aggregates.dto.EmpresaDTO;
import com.codigo.msperezhuatuco.infraestructure.entity.EmpresaEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class EmpresaMapper {

    public static EmpresaDTO fromEntity(EmpresaEntity empresaEntity)
    {
        EmpresaDTO dto1=new EmpresaDTO();
        dto1.setId(empresaEntity.getIdEmpresa());
        dto1.setRazonSocial(empresaEntity.getRazonSocial());
        dto1.setNumeroDocumento(empresaEntity.getNumeroDocumento());
        dto1.setTipoDocumento(empresaEntity.getTipoDocumento());
        dto1.setEstado(empresaEntity.getEstado());
        dto1.setCondicion(empresaEntity.getCondicion());
        dto1.setDireccion(empresaEntity.getDireccion());
        dto1.setDistrito(empresaEntity.getDistrito());
        dto1.setProvincia(empresaEntity.getProvincia());
        dto1.setDepartamento(empresaEntity.getDepartamento());
        dto1.setEsAgenteRetencion(empresaEntity.isEsAgenteRetencion());
        dto1.setUsuaCrea(empresaEntity.getUsuaCrea());
        dto1.setDateCreate(empresaEntity.getDateCreate());
        dto1.setUsuaModif(empresaEntity.getUsuaModif());
        dto1.setDateModif(empresaEntity.getDateModif());
        dto1.setUsuaDelet(empresaEntity.getUsuaDelet());
        dto1.setDateDelet(empresaEntity.getDateDelet());
        return dto1;
    }

}
