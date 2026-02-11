package com.example.patitas.Controller;

import com.example.patitas.Dtos.*;
import com.example.patitas.Model.Servicio;
import com.example.patitas.Service.DiaAgendaService;
import com.example.patitas.Service.DisponibilidadService;
import com.example.patitas.Service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ServicioService servicioService;
    @Autowired
    private DisponibilidadService disponibilidadService;
    @Autowired
    private DiaAgendaService diaAgendaService;

    @PostMapping("/nuevoServicio")
    public void nuevoServicio(@RequestBody NuevoServicioRequestDto dto){
        servicioService.nuevoServicio(dto);
    }
    @PostMapping("/editarPrecio")
    public void editarPrecioServicio(@RequestBody EditarPreciosServicioRequestDto dto){
        servicioService.editarPrecio(dto);
    }
    @GetMapping("/obtenerServicio")
    public List<ServicioDto> obtenerServicio(){
        return servicioService.obtenerServicios();
    }
    @PostMapping("/servicioDisponible")
    public void cambiarDisponibilidad(@RequestParam Long idServicio){
        servicioService.cambiarDisponibilidad(idServicio);
    }
    @PostMapping("/crearDisponibilidad")
    public CodigoRespondDto crearRango(@RequestParam Long idDia, @RequestBody CrearDisponibilidadRequestDto dto){
        return disponibilidadService.crearDisponibilidad(idDia,dto);
    }
    @GetMapping("/estadoDias")
    public List<EstadoDiasRespondDto> obtenerEstadoDias(){
        return diaAgendaService.obtenerDias();
    }
    @PostMapping("/editarEstadoDia")
    public void editarDisponibilidadDia(@RequestParam Long idDia){
        diaAgendaService.CambiarDisponibilidad(idDia);
    }
    @GetMapping("/obtenerRangosDia")
    public List<RangosPorDiaAdminDto> obtenerRangosPorDia(){
        return disponibilidadService.obtenerRangosPorDia();
    }
    @DeleteMapping("/borrarRango")
    public void borrarRango(@RequestParam Long idRango){
        disponibilidadService.borrarRango(idRango);
    }

}
