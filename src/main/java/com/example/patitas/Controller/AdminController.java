package com.example.patitas.Controller;

import com.example.patitas.Dtos.EditarPreciosServicioRequestDto;
import com.example.patitas.Dtos.NuevoServicioRequestDto;
import com.example.patitas.Model.Servicio;
import com.example.patitas.Service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    ServicioService service;

    @PostMapping("/nuevoServicio")
    public void nuevoServicio(@RequestBody NuevoServicioRequestDto dto){
        service.nuevoServicio(dto);
    }
    @PostMapping("/editarPrecio")
    public void editarPrecioServicio(@RequestBody EditarPreciosServicioRequestDto dto){
        service.editarPrecio(dto);
    }
    @GetMapping("/obtenerServicio")
    public List<Servicio> obtenerServicio(){
        return service.obtenerServicios();
    }
    @PostMapping("/servicioDisponible")
    public void cambiarDisponibilidad(@RequestParam Long idServicio){
        service.cambiarDisponibilidad(idServicio);

    }

}
