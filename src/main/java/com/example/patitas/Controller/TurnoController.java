package com.example.patitas.Controller;

import com.example.patitas.Dtos.ServicioDto;
import com.example.patitas.Service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {
    @Autowired
    ServicioService servicioService;
    @GetMapping("/obtenerServicio")
    public List<ServicioDto> obtenerServicio(){
        return servicioService.obtenerServicios();
    }
}
