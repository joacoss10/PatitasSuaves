package com.example.patitas.Service;

import com.example.patitas.Dtos.CodigoRespondDto;
import com.example.patitas.Dtos.CrearDisponibilidadRequestDto;
import com.example.patitas.Dtos.DisponibilidadRespondDto;
import com.example.patitas.Dtos.RangosPorDiaAdminDto;
import com.example.patitas.Model.DiaAgenda;
import com.example.patitas.Model.Enums.DiaSemanaAgenda;
import com.example.patitas.Model.Enums.EstadoTurno;
import com.example.patitas.Model.RangoHorario;
import com.example.patitas.Model.Turno;
import com.example.patitas.Repository.DiaAgendaRepository;
import com.example.patitas.Repository.RangoHorarioRepository;
import com.example.patitas.Repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DisponibilidadService {
    @Autowired
    private RangoHorarioRepository repository;
    @Autowired
    private DiaAgendaService diaAgendaService;
    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private RangoHorarioRepository rangoHorarioRepository;
    @Autowired
    private DiaAgendaRepository diaAgendaRepository;
    private static final int duracionTurnoMin = 90;
    private static final ZoneId ZONE_AR = ZoneId.of("America/Argentina/Buenos_Aires");
    private static final DateTimeFormatter HORA_FMT = DateTimeFormatter.ofPattern("HH:mm");

    public List<DisponibilidadRespondDto> obtenerDisponibilidad() {

        LocalDate desde = LocalDate.now(ZONE_AR);
        LocalDate hasta = desde.plusDays(15);

        LocalDate hoy = LocalDate.now(ZONE_AR);
        LocalTime ahora = LocalTime.now(ZONE_AR);

        List<EstadoTurno> estadosQueBloquean =
                List.of(EstadoTurno.AConfirmar, EstadoTurno.Confirmado);

        List<Turno> turnos =
                turnoRepository.findByFechaBetweenAndEstadoIn(desde, hasta, estadosQueBloquean);

        Map<LocalDate, Set<LocalTime>> ocupadosPorFecha = turnos.stream()
                .collect(Collectors.groupingBy(
                        Turno::getFecha,
                        Collectors.mapping(Turno::getHoraInicio, Collectors.toSet())
                ));
        List<DisponibilidadRespondDto> salida = new ArrayList<>();

        for (LocalDate fecha = desde; !fecha.isAfter(hasta); fecha = fecha.plusDays(1)) {

            DisponibilidadRespondDto disAux = new DisponibilidadRespondDto();
            disAux.setFecha(fecha.toString());

            DiaSemanaAgenda diaSemanaAgenda = mapearDiaSemana(fecha.getDayOfWeek());

            if (diaSemanaAgenda == null) {
                disAux.setHorario(List.of());
                salida.add(disAux);
                continue;
            }
            Optional<DiaAgenda> optDia = diaAgendaService.encontrarDia(diaSemanaAgenda);

            if (optDia.isEmpty() || !optDia.get().isHabilitado()) {
                disAux.setHorario(List.of());
                salida.add(disAux);
                continue;
            }
            List<RangoHorario> rangos =
                    rangoHorarioRepository.findByDiaAgendaAndHabilitadoTrue(optDia.get());

            Set<LocalTime> ocupados =
                    ocupadosPorFecha.getOrDefault(fecha, Collections.emptySet());

            List<String> disponibles = new ArrayList<>();

            for (RangoHorario rango : rangos) {

                LocalTime t = rango.getHoraInicio();

                while (!t.plusMinutes(duracionTurnoMin).isAfter(rango.getHoraFin())) {

                    boolean yaPaso = fecha.equals(hoy)
                            && t.plusMinutes(duracionTurnoMin).isBefore(ahora);

                    if (!ocupados.contains(t) && !yaPaso) {
                        disponibles.add(t.format(HORA_FMT));
                    }

                    t = t.plusMinutes(duracionTurnoMin);
                }
            }

            disponibles.sort(Comparator.naturalOrder());
            disAux.setHorario(disponibles);
            salida.add(disAux);
        }
        return salida;
    }



    public CodigoRespondDto crearDisponibilidad(Long idDiaSemana, CrearDisponibilidadRequestDto dto) {

        CodigoRespondDto respondDto = new CodigoRespondDto();

        if (dto == null || dto.getHoraInicio() == null || dto.getHoraFin() == null) {
            respondDto.setCodigo(5102);
            respondDto.setMensaje("Horas invalidas");
            return respondDto;
        }

        LocalTime inicio = dto.getHoraInicio();
        LocalTime fin = dto.getHoraFin();

        if (!inicio.isBefore(fin)) {
            respondDto.setCodigo(5102);
            respondDto.setMensaje("Hora inicio debe ser menor a hora fin");
            return respondDto;
        }

        if (Duration.between(inicio, fin).toMinutes() < duracionTurnoMin) {
            respondDto.setCodigo(5103);
            respondDto.setMensaje("El rango debe permitir al menos un turno de 90 minutos");
            return respondDto;
        }

        Optional<DiaAgenda> optDia = diaAgendaService.encontrarDia(idDiaSemana);

        if (optDia.isEmpty()) {
            respondDto.setCodigo(5101);
            respondDto.setMensaje("Dia no encontrado");
            return respondDto;
        }

        List<RangoHorario> existentes =
                rangoHorarioRepository.findByDiaAgendaId(idDiaSemana);

        for (RangoHorario r : existentes) {

            if (!r.isHabilitado()) continue;

            if (r.getHoraInicio().equals(inicio) && r.getHoraFin().equals(fin)) {
                respondDto.setCodigo(5105);
                respondDto.setMensaje("El rango ya existe");
                return respondDto;
            }

            if (seSuperpone(inicio, fin, r.getHoraInicio(), r.getHoraFin())) {
                respondDto.setCodigo(5104);
                respondDto.setMensaje("El rango se superpone con otro rango existente");
                return respondDto;
            }
        }

        RangoHorario nuevoRango = new RangoHorario();
        nuevoRango.setDiaAgenda(optDia.get());
        nuevoRango.setHoraInicio(inicio);
        nuevoRango.setHoraFin(fin);
        nuevoRango.setHabilitado(true);

        rangoHorarioRepository.save(nuevoRango);

        respondDto.setCodigo(2100);
        respondDto.setMensaje("Creado con exito");
        return respondDto;
    }


    private DiaSemanaAgenda mapearDiaSemana(DayOfWeek dow) {
        return switch (dow) {
            case MONDAY -> DiaSemanaAgenda.Lunes;
            case TUESDAY -> DiaSemanaAgenda.Martes;
            case WEDNESDAY -> DiaSemanaAgenda.Miercoles;
            case THURSDAY -> DiaSemanaAgenda.Jueves;
            case FRIDAY -> DiaSemanaAgenda.Viernes;
            case SATURDAY -> DiaSemanaAgenda.Sabado;
            case SUNDAY -> null;
        };
    }
    private boolean seSuperpone(LocalTime a, LocalTime b, LocalTime c, LocalTime d) {
        return a.isBefore(d) && c.isBefore(b);
    }
    public List<RangosPorDiaAdminDto> obtenerRangosPorDia() {

        List<RangosPorDiaAdminDto> respond = new ArrayList<>();
        List<DiaAgenda> habilitados = diaAgendaRepository.findByHabilitadoTrue();

        for (DiaAgenda diaAux : habilitados) {

            List<RangoHorario> rangosDia = rangoHorarioRepository.findByDiaAgendaId(diaAux.getId());

            for (RangoHorario rangoaux : rangosDia) {

                RangosPorDiaAdminDto rangoRespond = new RangosPorDiaAdminDto();
                rangoRespond.setDia(diaAux.getDiaSemana());

                rangoRespond.setHabilitado(rangoaux.isHabilitado());
                rangoRespond.setId(rangoaux.getId());
                rangoRespond.setHoraInicio(rangoaux.getHoraInicio());
                rangoRespond.setHoraFin(rangoaux.getHoraFin());

                List<String> turnosGenerados = new ArrayList<>();
                LocalTime t = rangoaux.getHoraInicio();

                while (!t.plusMinutes(duracionTurnoMin).isAfter(rangoaux.getHoraFin())) {
                    turnosGenerados.add(t.format(HORA_FMT));
                    t = t.plusMinutes(duracionTurnoMin);
                }

                rangoRespond.setTurnosGenerados(turnosGenerados);

                respond.add(rangoRespond);
            }
        }

        return respond;
    }
    public void borrarRango(Long idRango){
        rangoHorarioRepository.deleteById(idRango);
    }

}
