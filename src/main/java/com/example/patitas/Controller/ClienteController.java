package com.example.patitas.Controller;

import com.example.patitas.Dtos.*;
import com.example.patitas.Model.Enums.EstadoTurno;
import com.example.patitas.Service.ClienteService;
import com.example.patitas.Service.LoginService;
import com.example.patitas.Service.PerroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/me")
public class ClienteController {
    @Autowired
   private ClienteService clienteService;
    @Autowired
    private PerroService perroService;
    @Autowired
    private LoginService loginService;
    @GetMapping("/datos")
    public DatosClienteRespondDto obtenerDatosCliente(){
        return clienteService.obtenerDatosCliente();
    }

    @GetMapping("/perros")
    public List<MisPerrosRespondDto> obtenerPerroCliente(){
        return perroService.obtenerPerrosCliente();
    }
    @PostMapping("/modificarDatos")
    public void modificarDatosCliente(@RequestBody ModificacionDatosClienteRequestDto requestDto){
        clienteService.ModificarDatosCliente(requestDto);
    }
    @GetMapping("/turnos")
    public List<TurnoClienteRespondDto> obtenerTurnosCliente(@RequestParam EstadoTurno estadoTurno){
        return clienteService.obtenerTurnosCliente(estadoTurno);
    }
    @PostMapping("/modificarContrasenia")
    public void modificarContrasenia(@RequestBody OlvidoDeContraseniaRequest request){
        loginService.cambiarContrasenia(request);
    }
}
