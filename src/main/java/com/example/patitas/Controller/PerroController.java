package com.example.patitas.Controller;

import com.example.patitas.Dtos.CodigoRespondDto;
import com.example.patitas.Dtos.MisPerrosRespondDto;
import com.example.patitas.Dtos.RegisterRequestDto;
import com.example.patitas.Dtos.RegistroPerroRequestDto;
import com.example.patitas.Service.PerroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perros")
public class PerroController {
    @Autowired
    private PerroService service;
    @PostMapping("/registro")
    public CodigoRespondDto registrarPerro(@RequestBody RegistroPerroRequestDto dto){
        return service.registrarPerro(dto);
    }
     @GetMapping("/misperros")
    public List<MisPerrosRespondDto> obtenerPerroCliente(@RequestParam Long idCliente){
        return service.obtenerPerrosCliente(idCliente);
    }
    @DeleteMapping()
    public void eliminarPerro(@RequestParam Long idPerro){
        service.eliminarPerro(idPerro);
    }
}
