package com.example.patitas.Service;

import com.example.patitas.Dtos.*;
import com.example.patitas.Model.*;
import com.example.patitas.Model.Enums.EstadoTurno;
import com.example.patitas.Repository.ClienteRepository;
import com.example.patitas.Repository.TurnoItemRepository;
import com.example.patitas.Repository.TurnoRepository;
import com.example.patitas.Util.SecurityUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private TurnoItemRepository turnoItemRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PerroService perroService;
    @Autowired
    private ServicioService servicioService;
    @Autowired
    private SecurityUtils securityUtils;

    @Transactional
    public void generarTurno(GenerarTurnoRequestDto dto) {

        if (dto == null || dto.getFecha() == null || dto.getHoraInicio() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Datos incompletos");
        }

        Long clienteId = securityUtils.getClienteId();
        if (clienteId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        if (dto.getItems() == null || dto.getItems().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe seleccionar al menos un perro");
        }

        if (dto.getItems().size() > 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Máximo 3 perros por turno");
        }

        boolean ocupado = turnoRepository
                .existsByFechaAndHoraInicioAndEstadoIn(
                        dto.getFecha(),
                        dto.getHoraInicio(),
                        List.of(EstadoTurno.AConfirmar, EstadoTurno.Confirmado)
                );

        if (ocupado) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Turno existente");
        }

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Turno turno = new Turno();
        turno.setFecha(dto.getFecha());
        turno.setHoraInicio(dto.getHoraInicio());
        turno.setCliente(cliente);
        turno.setEstado(EstadoTurno.AConfirmar);

        double precioTotal = 0;
        List<TurnoItem> items = new ArrayList<>();

        for (GenerarTurnoItemRequestDto itemDto : dto.getItems()) {

            Optional<Perro> perro = perroService.perroCliente(clienteId, itemDto.getPerroId());
            Optional<Servicio> servicio = servicioService.obtenerServico(itemDto.getServicioId());
            if (perro.isEmpty() || servicio.isEmpty() ) {
               throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Perro o servicio no encontrado");
            } else if(!perro.get().isActivo()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Perro o servicio no encontrado");
            }else{
                TurnoItem item = new TurnoItem();
                item.setTurno(turno);
                item.setPerro(perro.get());
                item.setServicio(servicio.get());
                item.setPrecio(servicio.get().getPrecio());
                item.setNotas(perro.get().getObservaciones());
                items.add(item);
                precioTotal += servicio.get().getPrecio();

            }

        }
        turno.setItems(items);
        turno.setTotal(precioTotal);
        turnoRepository.save(turno);
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
    public void cancelarTurno(Long idTurno) {
        Turno turno = turnoRepository.findByClienteIdAndId(securityUtils.getClienteId(), idTurno).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        turno.setEstado(EstadoTurno.Cancelado);
        turnoRepository.save(turno);
    }
}
