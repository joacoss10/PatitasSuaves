package com.example.patitas.Controller;

import com.example.patitas.Dtos.CodigoRespondDto;
import com.example.patitas.Dtos.GenerarTurnoDto;
import com.example.patitas.Dtos.ServicioDto;
import com.example.patitas.Dtos.TurnoResumenClienteDto;
import com.example.patitas.Service.ServicioService;
import com.example.patitas.Service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {
    @Autowired
    ServicioService servicioService;
    @Autowired
    TurnoService turnoService;
    @GetMapping("/obtenerServicio")
    public List<ServicioDto> obtenerServicio(){
        return servicioService.obtenerServicios();
    }
    @PostMapping("/generarTurno")
    public CodigoRespondDto generarTurno(@RequestBody GenerarTurnoDto dto){
        return turnoService.generarTurno(dto);
    }

}
