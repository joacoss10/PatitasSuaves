package com.example.patitas.Service;

import com.example.patitas.Dtos.EditarPreciosServicioRequestDto;
import com.example.patitas.Dtos.NuevoServicioRequestDto;
import com.example.patitas.Model.Servicio;
import com.example.patitas.Repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioService {
    @Autowired
    ServicioRepository repository;
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
    public List<Servicio>obtenerServicios(){
        return repository.findAll();
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
}
