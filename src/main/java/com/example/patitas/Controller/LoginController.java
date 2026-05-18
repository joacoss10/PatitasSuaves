package com.example.patitas.Controller;

import com.example.patitas.Dtos.TokenRespondDto;
import com.example.patitas.Dtos.IncioSesionRequestDto;
import com.example.patitas.Dtos.RegisterRequestDto;
import com.example.patitas.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private LoginService service;
    @PostMapping("/register")
    public void registrarUsuario(@RequestBody RegisterRequestDto dto){
             service.registrar(dto);
    }
    @PostMapping("/login")
    public TokenRespondDto iniciarSesion (@RequestBody IncioSesionRequestDto dto){
        return service.iniciarSesion(dto);
    }
}
