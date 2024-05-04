package com.codigo.msperezhuatuco.infraestructure.adapters;

import com.codigo.msperezhuatuco.domain.aggregates.constants.Constant;
import com.codigo.msperezhuatuco.domain.aggregates.dto.EmpresaDTO;
import com.codigo.msperezhuatuco.domain.aggregates.dto.SunatDTO;
import com.codigo.msperezhuatuco.domain.aggregates.request.EmpresaRequest;
import com.codigo.msperezhuatuco.domain.ports.out.EmpresaServiceOut;
import com.codigo.msperezhuatuco.infraestructure.client.ClientSunat;
import com.codigo.msperezhuatuco.infraestructure.dao.EmpresaRepository;
import com.codigo.msperezhuatuco.infraestructure.entity.EmpresaEntity;
import com.codigo.msperezhuatuco.infraestructure.mapper.EmpresaMapper;
import com.codigo.msperezhuatuco.infraestructure.redis.RedisService;
import com.codigo.msperezhuatuco.infraestructure.util.Util;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class EmpresaAdapter implements EmpresaServiceOut {
    @Autowired
    private  EmpresaRepository empresaRepository;
    @Autowired
    private ClientSunat   clientSunat;
    @Autowired
    private  RedisService redisService;

    @Value("${token.reniec}")
    private String tokenSunat;

    @Override
    public EmpresaDTO crearEmpresaOut(EmpresaRequest empresaRequest) {
        EmpresaEntity empresaEntity = getEntity(empresaRequest,1,null);
        return EmpresaMapper.fromEntity(empresaRepository.save(empresaEntity));
    }

    private EmpresaEntity getEntity(EmpresaRequest empresaRequest, int actuar, Long id){
        SunatDTO sunatDTO = getExecSunat(empresaRequest.getNumDoc());
        EmpresaEntity entity = new EmpresaEntity();

        if (actuar==1) {

            entity.setRazonSocial(sunatDTO.getRazonSocial());
            entity.setTipoDocumento(sunatDTO.getTipoDocumento());
            entity.setNumeroDocumento(sunatDTO.getNumeroDocumento());
            entity.setEstado(Constant.STATUS_ACTIVE);
            entity.setCondicion(sunatDTO.getCondicion());
            entity.setDireccion(sunatDTO.getDireccion());
            entity.setDistrito(sunatDTO.getDistrito());
            entity.setProvincia(sunatDTO.getProvincia());
            entity.setDepartamento(sunatDTO.getDepartamento());
            entity.setEsAgenteRetencion(sunatDTO.isEsAgenteRetencion());
            entity.setUsuaCrea(Constant.USU_ADMIN);
            entity.setDateCreate(getTimestamp());

        } else if (actuar==2) {
            entity.setIdEmpresa(id);
            entity.setRazonSocial(sunatDTO.getRazonSocial());
            entity.setTipoDocumento(sunatDTO.getTipoDocumento());
            entity.setNumeroDocumento(sunatDTO.getNumeroDocumento());
            entity.setEstado(Constant.STATUS_ACTIVE);
            entity.setCondicion(sunatDTO.getCondicion());
            entity.setDireccion(sunatDTO.getDireccion());
            entity.setDistrito(sunatDTO.getDistrito());
            entity.setProvincia(sunatDTO.getProvincia());
            entity.setDepartamento(sunatDTO.getDepartamento());
            entity.setEsAgenteRetencion(sunatDTO.isEsAgenteRetencion());
            entity.setUsuaModif(Constant.USU_ADMIN);
            entity.setDateModif(getTimestamp());

        }

        return entity;
    }


    private SunatDTO getExecSunat(String numDoc){
        String authorization = "Bearer "+tokenSunat;
        return clientSunat.getInfoSunat(numDoc,authorization);
    }
    private Timestamp getTimestamp(){
        long currenTIme = System.currentTimeMillis();
        return new Timestamp(currenTIme);
    }




    @Override
    public Optional<EmpresaDTO> buscarEmpresaxIdOut(Long id) {
        String redisInfo = redisService.getFromRedis(Constant.REDIS_KEY_OBTENEREMPRESA+id);
        if(redisInfo!= null){
            EmpresaDTO personaDto = Util.convertirDesdeString(redisInfo,EmpresaDTO.class);
            return Optional.of(personaDto);
        }else{
            EmpresaDTO personaDto = EmpresaMapper.fromEntity(empresaRepository.findById(id).get());
            String dataForRedis = Util.convertirAStringEmpresa(personaDto);
            redisService.saveInRedis(Constant.REDIS_KEY_OBTENEREMPRESA+id,dataForRedis,2);
            return Optional.of(personaDto);
        }
    }

    @Override
    public List<EmpresaDTO> buscarEmpresaTodosOut() {
        List<EmpresaDTO> listaDto = new ArrayList<>();
        List<EmpresaEntity> entidades = empresaRepository.findAll();
        for (EmpresaEntity dato :entidades){
            listaDto.add(EmpresaMapper.fromEntity(dato));
        }
        return listaDto;
    }


    @Override
    public EmpresaDTO actualizarEmpresaOut(Long id, EmpresaRequest empresaRequest) {
        Optional<EmpresaEntity> datoExtraido = empresaRepository.findById(id);
        if (datoExtraido.isPresent()) {
            EmpresaEntity existingEntity = datoExtraido.get();
            EmpresaEntity updatedEntity = getEntity(empresaRequest, 2, id);
            // Conservar los valores existentes de usuaCrea y dateCreate
            updatedEntity.setUsuaCrea(existingEntity.getUsuaCrea());
            updatedEntity.setDateCreate(existingEntity.getDateCreate());
            return EmpresaMapper.fromEntity(empresaRepository.save(updatedEntity));
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public EmpresaDTO deleteEmpresaOut(Long id) {
        Optional<EmpresaEntity> datoExtraido = empresaRepository.findById(id);
        if(datoExtraido.isPresent()){
            datoExtraido.get().setEstado(0);
            datoExtraido.get().setUsuaDelet(Constant.USU_ADMIN);
            datoExtraido.get().setDateDelet(getTimestamp());
            return EmpresaMapper.fromEntity(empresaRepository.save(datoExtraido.get()));
        }else {
            throw new RuntimeException();
        }
    }
}
