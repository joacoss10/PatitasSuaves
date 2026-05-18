package com.example.patitas.Controller;

import com.example.patitas.Dtos.TokenRespondDto;
import com.example.patitas.Dtos.RegistroPerroRequestDto;
import com.example.patitas.Service.PerroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/perros")
public class PerroController {
    @Autowired
    private PerroService service;
    @PostMapping("/registro")
    public void registrarPerro(@RequestBody RegistroPerroRequestDto dto){
      service.registrarPerro(dto);
    }
    @DeleteMapping()
    public void eliminarPerro(@RequestParam Long idPerro){
        service.eliminarPerro(idPerro);
    }
}
