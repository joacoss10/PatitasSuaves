package com.example.patitas.Controller;

import com.example.patitas.Dtos.DisponibilidadRespondDto;
import com.example.patitas.Service.DisponibilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth/agenda")
public class DisponibilidadController {
    @Autowired
    private DisponibilidadService disponibilidadService;

    @GetMapping()
    public List<DisponibilidadRespondDto> disponibilidad(){
        return disponibilidadService.obtenerDisponibilidad();
    }

}
