package com.example.patitas.Service;

import com.example.patitas.Dtos.DatosClienteRespondDto;
import com.example.patitas.Dtos.ModificacionDatosClienteRequestDto;
import com.example.patitas.Dtos.TurnoClienteRespondDto;
import com.example.patitas.Dtos.TurnoItemClienteRespondDto;
import com.example.patitas.Model.Cliente;
import com.example.patitas.Model.Enums.EstadoTurno;
import com.example.patitas.Model.Turno;
import com.example.patitas.Model.TurnoItem;
import com.example.patitas.Repository.ClienteRepository;
import com.example.patitas.Repository.TurnoRepository;
import com.example.patitas.Util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private SecurityUtils securityUtils;

    public Optional<Cliente> encontrarCliente(Long id){
      return repository.findById(id);
    }
    public DatosClienteRespondDto obtenerDatosCliente(){

        DatosClienteRespondDto respond=new DatosClienteRespondDto();
        Optional<Cliente> clienteOptional=repository.findById(securityUtils.getClienteId());
        if(clienteOptional.isPresent()){
            respond.setMail(clienteOptional.get().getEmail());
            respond.setNombre(clienteOptional.get().getNombre());
            respond.setTelefono(clienteOptional.get().getCelular());
        }
        return respond;
    }
    public void ModificarDatosCliente(ModificacionDatosClienteRequestDto request){
        Optional<Cliente> clienteOptional=repository.findById(securityUtils.getClienteId());
        if(clienteOptional.isPresent()){
            clienteOptional.get().setNombre(request.getNombre());
            clienteOptional.get().setCelular(request.getTelefono());
            repository.save(clienteOptional.get());
        }
    }
    public List<TurnoClienteRespondDto> obtenerTurnosCliente(EstadoTurno estado){
        List<Turno> turnos= turnoRepository.findByClienteIdAndEstado(securityUtils.getClienteId(), estado);
        List<TurnoClienteRespondDto> respond=new ArrayList<>();
        for(Turno turno:turnos){
            TurnoClienteRespondDto respondAux=new TurnoClienteRespondDto();
            respondAux.setHora(turno.getHoraInicio());
            respondAux.setId(turno.getId());
            respondAux.setFecha(turno.getFecha());
            respondAux.setEstado(turno.getEstado());
            respondAux.setPrecio(turno.getTotal());
            List<TurnoItem>items=turno.getItems();
            List<TurnoItemClienteRespondDto> respondItems=new ArrayList<>();
            for(TurnoItem itemAux:items){
                TurnoItemClienteRespondDto respondItemAux=new TurnoItemClienteRespondDto();
                respondItemAux.setPerro(itemAux.getPerro().getNombre());
                respondItemAux.setServicio(itemAux.getServicio().getServicio());
                respondItems.add(respondItemAux);
            }
            respondAux.setItems(respondItems);
            respond.add(respondAux);
        }
        return respond;
    }
}
