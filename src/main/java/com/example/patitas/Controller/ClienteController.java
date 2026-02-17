package com.example.patitas.Controller;

import com.example.patitas.Dtos.DatosClienteRespondDto;
import com.example.patitas.Dtos.MisPerrosRespondDto;
import com.example.patitas.Dtos.ModificacionDatosClienteRequestDto;
import com.example.patitas.Service.ClienteService;
import com.example.patitas.Service.PerroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    ClienteService clienteService;
    @Autowired
    PerroService perroService;
    @GetMapping("/datos")
    public DatosClienteRespondDto obtenerDatosCliente(@RequestParam long idCliente){
        return clienteService.obtenerDatosCliente(idCliente);
    }

    @GetMapping("/misperros")
    public List<MisPerrosRespondDto> obtenerPerroCliente(@RequestParam Long idCliente){
        return perroService.obtenerPerrosCliente(idCliente);
    }
    @PostMapping("/modificarDatos")
    public void modificarDatosCliente(ModificacionDatosClienteRequestDto requestDto){
        clienteService.ModificarDatosCliente(requestDto);
    }
}
