package com.codigo.msperezhuatuco.infraestructure.adapters;

import com.codigo.msperezhuatuco.domain.aggregates.constants.Constant;
import com.codigo.msperezhuatuco.domain.aggregates.dto.EmpresaDTO;
import com.codigo.msperezhuatuco.domain.aggregates.dto.PersonaDTO;
import com.codigo.msperezhuatuco.domain.aggregates.dto.ReniecDTO;
import com.codigo.msperezhuatuco.domain.aggregates.request.EmpresaRequest;
import com.codigo.msperezhuatuco.domain.aggregates.request.PersonaRequest;
import com.codigo.msperezhuatuco.domain.ports.out.PersonaServiceOut;
import com.codigo.msperezhuatuco.infraestructure.client.ClientReniec;
import com.codigo.msperezhuatuco.infraestructure.dao.PersonaRepository;
import com.codigo.msperezhuatuco.infraestructure.entity.EmpresaEntity;
import com.codigo.msperezhuatuco.infraestructure.entity.PersonaEntity;
import com.codigo.msperezhuatuco.infraestructure.mapper.EmpresaMapper;
import com.codigo.msperezhuatuco.infraestructure.mapper.PersonaMapper;
import com.codigo.msperezhuatuco.infraestructure.redis.RedisService;
import com.codigo.msperezhuatuco.infraestructure.util.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service

public class PersonaAdapter implements PersonaServiceOut {
    @Autowired
    private  PersonaRepository personaRepository;
    @Autowired
    private ClientReniec clientReniec;
    @Autowired
    private RedisService redisService;

    @Value("${token.reniec}")
    private String tokenReniec;
    @Override
    public PersonaDTO crearPersonaOut(PersonaRequest personaRequest) {
        PersonaEntity personaEntity = getEntity(personaRequest,1, null);
        return PersonaMapper.fromEntity(personaRepository.save(personaEntity));
    }

    private PersonaEntity getEntity(PersonaRequest personaRequest, int actuar, Long id){
        //Exec servicio
        ReniecDTO reniecDto = getExecReniec(personaRequest.getNumDoc());
        PersonaEntity entity = new PersonaEntity();

        if (actuar==1) {
            entity.setNumDocu(reniecDto.getNumeroDocumento());
            entity.setNombres(reniecDto.getNombres());
            entity.setApeMat(reniecDto.getApellidoMaterno());
            entity.setApePat(reniecDto.getApellidoPaterno());
            entity.setEstado(Constant.STATUS_ACTIVE);
            //Datos de auditoria donde corresponda
            entity.setUsuaCrea(Constant.USU_ADMIN);
            entity.setDateCreate(getTimestamp());
        }

        else if (actuar==2)
        {
            entity.setIdPersona(id);
            entity.setNumDocu(reniecDto.getNumeroDocumento());
            entity.setNombres(reniecDto.getNombres());
            entity.setApeMat(reniecDto.getApellidoMaterno());
            entity.setApePat(reniecDto.getApellidoPaterno());
            entity.setEstado(Constant.STATUS_ACTIVE);
            entity.setUsuaModif(Constant.USU_ADMIN);
            entity.setDateModif(getTimestamp());

    }

        return entity;

    }


    private ReniecDTO getExecReniec(String numDoc){
        String authorization = "Bearer "+tokenReniec;
        return clientReniec.getInfoReniec(numDoc,authorization);
    }
    private Timestamp getTimestamp(){
        long currenTIme = System.currentTimeMillis();
        return new Timestamp(currenTIme);
    }
    @Override
    public Optional<PersonaDTO> buscarPersonaxIdOut(Long id) {
        String redisInfo = redisService.getFromRedis(Constant.REDIS_KEY_OBTENERPERSONA+id);
        if(redisInfo!= null){
            PersonaDTO personaDto = Util.convertirDesdeString(redisInfo,PersonaDTO.class);
            return Optional.of(personaDto);
        }else{
            PersonaDTO personaDto = PersonaMapper.fromEntity(personaRepository.findById(id).get());
            String dataForRedis = Util.convertirAStringPersona(personaDto);
            redisService.saveInRedis(Constant.REDIS_KEY_OBTENERPERSONA+id,dataForRedis,2);
            return Optional.of(personaDto);
        }
    }

    @Override
    public List<PersonaDTO> buscarPersonaTodosOut() {
        List<PersonaDTO> listaDto = new ArrayList<>();
        List<PersonaEntity> entidades = personaRepository.findAll();
        for (PersonaEntity dato :entidades){
            listaDto.add(PersonaMapper.fromEntity(dato));
        }
        return listaDto;
    }

    /*@Override
    public PersonaDTO actualizarPersonaOut(Long id, PersonaRequest personaRequest) {
        Optional<PersonaEntity> datoExtraido = personaRepository.findById(id);
        if(datoExtraido.isPresent()){
            PersonaEntity personaEntity = getEntity(personaRequest,true, id);
            return PersonaMapper.fromEntity(personaRepository.save(personaEntity));
        }else {
            throw new RuntimeException();
        }
    }
*/
    @Override
    public PersonaDTO actualizarPersonaOut(Long id, PersonaRequest personaRequest) {
        Optional<PersonaEntity> datoExtraido = personaRepository.findById(id);
        if (datoExtraido.isPresent()) {
            PersonaEntity existingEntity = datoExtraido.get();
            PersonaEntity updatedEntity = getEntity(personaRequest, 2, id);
            // Conservar los valores existentes de usuaCrea y dateCreate
            updatedEntity.setUsuaCrea(existingEntity.getUsuaCrea());
            updatedEntity.setDateCreate(existingEntity.getDateCreate());
            return PersonaMapper.fromEntity(personaRepository.save(updatedEntity));
        } else {
            throw new RuntimeException();
        }
    }


    @Override
    public PersonaDTO deletePersonaOut(Long id) {
        Optional<PersonaEntity> datoExtraido = personaRepository.findById(id);
        if(datoExtraido.isPresent()){
            datoExtraido.get().setEstado(0);
            datoExtraido.get().setUsuaDelet(Constant.USU_ADMIN);
            datoExtraido.get().setDateDelet(getTimestamp());
            return PersonaMapper.fromEntity(personaRepository.save(datoExtraido.get()));
        }else {
            throw new RuntimeException();
        }
    }
}
