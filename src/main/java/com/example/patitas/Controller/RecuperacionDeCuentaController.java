package com.example.patitas.Controller;

import com.example.patitas.Dtos.TokenRespondDto;
import com.example.patitas.Dtos.OlvidoDeContraseniaRequest;
import com.example.patitas.Service.CodigoVerificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class RecuperacionDeCuentaController {
    @Autowired
    private CodigoVerificacionService codigoVerificacionService;
    @PostMapping("/obtenerCodigo")
    public void obtenerCodigoRecuperacion(@RequestBody OlvidoDeContraseniaRequest request){
        codigoVerificacionService.restablecerContraseniaRequest(request);
    }
    @PostMapping("/validarCodigo")
    public TokenRespondDto validarCodigo(@RequestBody OlvidoDeContraseniaRequest request){
        return codigoVerificacionService.verificarCodigo(request);
    }
}
