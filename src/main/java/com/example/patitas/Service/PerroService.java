package com.example.patitas.Service;


import com.example.patitas.Dtos.TokenRespondDto;
import com.example.patitas.Dtos.MisPerrosRespondDto;
import com.example.patitas.Dtos.RegistroPerroRequestDto;
import com.example.patitas.Exeptions.ApiException;
import com.example.patitas.Model.Cliente;
import com.example.patitas.Model.Perro;
import com.example.patitas.Repository.PerroRepository;
import com.example.patitas.Util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PerroService {
    @Autowired
   private PerroRepository repository;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private SecurityUtils securityUtils;

    public void registrarPerro(RegistroPerroRequestDto dto){
        TokenRespondDto respond=new TokenRespondDto();
        Optional<Cliente>clienteOptional=clienteService.encontrarCliente(securityUtils.getClienteId());
        if (clienteOptional.isPresent()){
            Optional<Perro> perroOptional=repository.findByNombreAndCliente_id(dto.getNombre(), clienteOptional.get().getId());
            if (perroOptional.isEmpty()) {
                Perro perro = new Perro();
                perro.setCliente(clienteOptional.get());
                perro.setNombre(dto.getNombre());
                perro.setObservaciones(dto.getObservaciones());
                perro.setTamanio(dto.getTamanioPerro());
                perro.setActivo(true);
                repository.save(perro);
            } else {
                Perro perro = perroOptional.get();
                if (!perro.isActivo()) {
                    perro.setActivo(true);
                    perro.setObservaciones(dto.getObservaciones());
                    perro.setTamanio(dto.getTamanioPerro());
                    repository.save(perro);
                } else {
                    repository.save(perro);
                    throw new ApiException("Perro ya registrado",HttpStatus.CONFLICT);
                }
            }
        }
    }
    public List<MisPerrosRespondDto>obtenerPerrosCliente(){
        Optional<Cliente>clienteOptional=clienteService.encontrarCliente(securityUtils.getClienteId());
        List<Perro>perroList=new ArrayList<>();
        List<MisPerrosRespondDto> respondDtoLis=new ArrayList<>();
        if(clienteOptional.isPresent()){
            perroList=repository.findByCliente_id(clienteOptional.get().getId());
        }
        for (Perro p:perroList){
            if(p.isActivo()){
            MisPerrosRespondDto aux=new MisPerrosRespondDto();
            aux.setObservaciones(p.getObservaciones());
            aux.setTamanioPerro(p.getTamanio());
            aux.setNombre(p.getNombre());
            aux.setId(p.getId());
            respondDtoLis.add(aux);
            }
        }
       return respondDtoLis;
    }
    public void eliminarPerro(Long idPerro){
        Optional<Perro> perro = repository.findByIdAndCliente_id(idPerro, securityUtils.getClienteId());
        if(perro.isPresent()){
            perro.get().setActivo(false);
            repository.save(perro.get());
        }else throw  new ApiException("Cliente o perro incorrcto",HttpStatus.NOT_FOUND);


    }
    public Optional<Perro> obtenerPerro(Long id){
        return repository.findById(id);
    }
    public Optional<Perro>perroCliente(Long idClinete, Long idPerro){
        return repository.findByIdAndCliente_id(idPerro,idClinete);
    }

}
