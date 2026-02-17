package com.example.patitas.Service;

import com.example.patitas.Dtos.*;
import com.example.patitas.Model.*;
import com.example.patitas.Model.Enums.EstadoTurno;
import com.example.patitas.Repository.ClienteRepository;
import com.example.patitas.Repository.TurnoItemRepository;
import com.example.patitas.Repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    TurnoRepository turnoRepository;
    @Autowired
    TurnoItemRepository turnoItemRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    PerroService perroService;
    @Autowired
    ServicioService servicioService;

    public CodigoRespondDto generarTurno(GenerarTurnoDto dto) {
        CodigoRespondDto respond = new CodigoRespondDto();
        if (dto == null || dto.getFecha() == null || dto.getHoraIncio() == null || dto.getIdCliente() == null) {
            respond.setCodigo(6004);
            respond.setMensaje("Datos incompletos");
            return respond;
        }
        Optional<Turno> existencia = turnoRepository.findByFechaAndHoraInicio(dto.getFecha(), dto.getHoraIncio());
        if (existencia.isPresent() && (existencia.get().getEstado().equals(EstadoTurno.AConfirmar)||existencia.get().getEstado().equals(EstadoTurno.Confirmado))) {
            respond.setCodigo(6001);
            respond.setMensaje("Turno existente");
            return respond;
        }
        Optional<Cliente> clienteOpt = clienteRepository.findById(dto.getIdCliente());
        if (clienteOpt.isEmpty()) {
            respond.setCodigo(6005);
            respond.setMensaje("Cliente inexistente");
            return respond;
        }
        Cliente cliente = clienteOpt.get();
         List<TurnoItemDto> turnoItems = dto.getItems();
        if (turnoItems == null || turnoItems.isEmpty()) {
            respond.setCodigo(6006);
            respond.setMensaje("Debe seleccionar al menos un perro");
            return respond;
        }
        if (turnoItems.size() > 3) {
            respond.setCodigo(6007);
            respond.setMensaje("Máximo 3 perros por turno");
            return respond;
        }

        double precioTotal = 0;

        Turno turno = new Turno();
        turno.setHoraInicio(dto.getHoraIncio());
        turno.setFecha(dto.getFecha());
        turno.setCliente(cliente);
        turno.setEstado(EstadoTurno.AConfirmar);

        List<TurnoItem> items = new ArrayList<>();


        for (TurnoItemDto turnoItemDtoAux : turnoItems) {

            Optional<Perro> perroOpt = perroService.obtenerPerro(turnoItemDtoAux.getPerroId());
            Optional<Servicio> servicioOpt = servicioService.obtenerServico(turnoItemDtoAux.getServicioId());

            if (perroOpt.isEmpty() || servicioOpt.isEmpty()) {
                respond.setCodigo(6002);
                respond.setMensaje("Perro o servicio inexistente");
                return respond;
            }

            Perro perro = perroOpt.get();
            Servicio servicio = servicioOpt.get();


            List<Perro> perrosDelCliente = cliente.getPerros();
            if (perrosDelCliente == null || !perrosDelCliente.contains(perro)) {
                respond.setCodigo(6003);
                respond.setMensaje("El perro no pertenece al cliente seleccionado");
                return respond;
            }

            TurnoItem turnoItem = new TurnoItem();
            turnoItem.setServicio(servicio);
            turnoItem.setPerro(perro);
            turnoItem.setPrecio(servicio.getPrecio());
            turnoItem.setNotas(perro.getObservaciones());


            turnoItem.setTurno(turno);

            items.add(turnoItem);
            precioTotal += servicio.getPrecio();
        }

        turno.setItems(items);
        turno.setTotal(precioTotal);

        turnoRepository.save(turno);

        respond.setCodigo(6000);
        respond.setMensaje("Turno generado correctamente");
        return respond;
    }
    public List<TurnoAdminDto> obtenerTurnosPendientes(){
        List<Turno> turnos=turnoRepository.findByEstado(EstadoTurno.AConfirmar);
        return mappeoTurno(turnos);
    }
    public List<TurnoAdminDto> obtenerProximosTurnos(){
        List<Turno> turnos=turnoRepository.findByEstadoAndFechaGreaterThanEqualOrderByFechaAscHoraInicioAsc(EstadoTurno.Confirmado, LocalDate.now());
        return mappeoTurno(turnos);
    }
    public void cambiarEstadoTurno(Long turnoId,EstadoTurno estadoTurno){
       Optional< Turno> turnoOpt=turnoRepository.findById(turnoId);
       if(turnoOpt.isPresent()){
           Turno turno=turnoOpt.get();
           turno.setEstado(estadoTurno);
           turnoRepository.save(turno);
       }
    }
    private List<TurnoAdminDto> mappeoTurno(List<Turno>turnos){
        List<TurnoAdminDto> respond=new ArrayList<>();
        for(Turno turnoAux:turnos){
            TurnoAdminDto respondAux=new TurnoAdminDto();
            respondAux.setDia(turnoAux.getFecha());
            respondAux.setId(turnoAux.getId());
            respondAux.setNombreCliente(turnoAux.getCliente().getNombre());
            respondAux.setCelularCliente(turnoAux.getCliente().getCelular());
            respondAux.setHora(turnoAux.getHoraInicio());
            respondAux.setTotal(turnoAux.getTotal());
            List<TurnoItem>items=turnoAux.getItems();
            List<TurnoAdminItemDto> adminItemDtos=new ArrayList<>();
            for(TurnoItem turnoItemAux:items){
                TurnoAdminItemDto itemRespondAux =new TurnoAdminItemDto();
                itemRespondAux.setNombrePerro(turnoItemAux.getPerro().getNombre());
                itemRespondAux.setNotas(turnoItemAux.getNotas());
                itemRespondAux.setServicio(turnoItemAux.getServicio().getServicio());
                itemRespondAux.setTamanio(turnoItemAux.getPerro().getTamanio().toString());
                adminItemDtos.add(itemRespondAux);
            }
            respondAux.setItem(adminItemDtos);
            respond.add(respondAux);
    }
        return respond;
    }
}
