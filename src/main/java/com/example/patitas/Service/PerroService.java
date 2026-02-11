package com.example.patitas.Service;


import com.example.patitas.Dtos.CodigoRespondDto;
import com.example.patitas.Dtos.MisPerrosRespondDto;
import com.example.patitas.Dtos.RegistroPerroRequestDto;
import com.example.patitas.Model.Cliente;
import com.example.patitas.Model.Perro;
import com.example.patitas.Repository.PerroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PerroService {
    @Autowired
   private PerroRepository repository;
    @Autowired
    private ClienteService clienteService;

    public CodigoRespondDto registrarPerro(RegistroPerroRequestDto dto){
        CodigoRespondDto respond=new CodigoRespondDto();
        Optional<Cliente>clienteOptional=clienteService.encontrarCliente(dto.getIdCliente());
        if (clienteOptional.isPresent()){
            Optional<Perro> perroOptional=repository.findByNombreAndCliente_id(dto.getNombre(), clienteOptional.get().getId());
            if(perroOptional.isEmpty()){
                Perro perro=new Perro();
                perro.setCliente(clienteOptional.get());
                perro.setNombre(dto.getNombre());
                perro.setObservaciones(dto.getObservaciones());
                perro.setTamanio(dto.getTamanioPerro());
                repository.save(perro);
                respond.setCodigo(2003);
                respond.setMensaje("Perro registrado");
            }else{
                respond.setCodigo(5004);
                respond.setMensaje("Perro ya existente");
            }
        }
        return respond;
    }
    public List<MisPerrosRespondDto>obtenerPerrosCliente(Long idCliente){
        Optional<Cliente>clienteOptional=clienteService.encontrarCliente(idCliente);
        List<Perro>perroList=new ArrayList<>();
        List<MisPerrosRespondDto> respondDtoLis=new ArrayList<>();
        if(clienteOptional.isPresent()){
            perroList=repository.findByCliente_id(clienteOptional.get().getId());
        }
        for (Perro p:perroList){
            MisPerrosRespondDto aux=new MisPerrosRespondDto();
            aux.setObservaciones(p.getObservaciones());
            aux.setTamanioPerro(p.getTamanio());
            aux.setNombre(p.getNombre());
            aux.setId(p.getId());
            respondDtoLis.add(aux);
        }
       return respondDtoLis;
    }
    public void eliminarPerro(Long id){
        repository.deleteById(id);
    }


}
