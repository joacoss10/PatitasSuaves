package com.example.patitas.Service;

import com.example.patitas.Dtos.EditarPreciosServicioRequestDto;
import com.example.patitas.Dtos.NuevoServicioRequestDto;
import com.example.patitas.Dtos.ServicioDto;
import com.example.patitas.Model.Servicio;
import com.example.patitas.Repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioService {
    @Autowired
    private ServicioRepository repository;
    public void nuevoServicio(NuevoServicioRequestDto dto){
        Servicio servicio=new Servicio();
        servicio.setServicio(dto.getNombreServicio());
        servicio.setPrecio(dto.getPrecioServicio());
        repository.save(servicio);
    }
    public void editarPrecio(EditarPreciosServicioRequestDto dto){
       Optional<Servicio> servicio= repository.findById(dto.getIdServicio());
        servicio.ifPresent(value -> value.setPrecio(dto.getPrecio()));
        repository.save(servicio.get());
    }
    public List<ServicioDto>obtenerServicios(){
        List<ServicioDto> respond=new ArrayList<>();
        List<Servicio> servicios=repository.findAll();

        for(Servicio servicioAux:servicios){
            ServicioDto respondAux=new ServicioDto();
            respondAux.setActivo(servicioAux.isActivo());
            respondAux.setId(servicioAux.getId());
            respondAux.setPrecio(servicioAux.getPrecio());
            respondAux.setServicio(servicioAux.getServicio());
            respond.add(respondAux);
        }
        return respond;
    }
    public void cambiarDisponibilidad(Long idServicio){
        Optional<Servicio> servicio= repository.findById(idServicio);
        if(servicio.isPresent()){
            if(servicio.get().isActivo()){
                servicio.get().setActivo(false);
            }else{
                servicio.get().setActivo(true);}
            repository.save( servicio.get());
        }
    }
    public Optional<Servicio>obtenerServico(Long id){
        return repository.findById(id);
    }
}
