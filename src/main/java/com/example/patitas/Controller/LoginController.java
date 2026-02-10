package com.example.patitas.Controller;

import com.example.patitas.Dtos.CodigoRespondDto;
import com.example.patitas.Dtos.IncioSesionRequestDto;
import com.example.patitas.Dtos.RegisterRequestDto;
import com.example.patitas.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private LoginService service;
    @PostMapping("/register")
    public CodigoRespondDto registrarUsuario(@RequestBody RegisterRequestDto dto){

        return service.registrar(dto);
    }
    @PostMapping("/login")
    public CodigoRespondDto iniciarSesion (@RequestBody IncioSesionRequestDto dto){
        return service.iniciarSesion(dto);
    }
}
