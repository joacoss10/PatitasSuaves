package com.example.patitas.Service;


import com.example.patitas.Dtos.CodigoRespondDto;
import com.example.patitas.Dtos.MisPerrosRespondDto;
import com.example.patitas.Dtos.RegistroPerroRequestDto;
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

    public CodigoRespondDto registrarPerro(RegistroPerroRequestDto dto){
        CodigoRespondDto respond=new CodigoRespondDto();
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
                respond.setCodigo(2003);
                respond.setMensaje("Perro registrado");
            } else {
                Perro perro = perroOptional.get();
                if (!perro.isActivo()) {
                    perro.setActivo(true);
                    perro.setObservaciones(dto.getObservaciones());
                    perro.setTamanio(dto.getTamanioPerro());
                    repository.save(perro);
                    respond.setCodigo(2004);
                    respond.setMensaje("Perro reactivado correctamente");
                } else {
                    respond.setCodigo(5004);
                    respond.setMensaje("Perro ya existente");
                }
            }
        }
        return respond;
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
        Perro perro = repository.findByIdAndCliente_id(idPerro, securityUtils.getClienteId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        perro.setActivo(false);
        repository.save(perro);

    }
    public Optional<Perro> obtenerPerro(Long id){
        return repository.findById(id);
    }
    public Optional<Perro>perroCliente(Long idClinete, Long idPerro){
        return repository.findByIdAndCliente_id(idPerro,idClinete);
    }

}
