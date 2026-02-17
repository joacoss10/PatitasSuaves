package com.example.patitas.Repository;

import com.example.patitas.Model.Enums.EstadoTurno;
import com.example.patitas.Model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    List<Turno> findByFechaBetweenAndEstadoIn(LocalDate desde, LocalDate hasta, Collection<EstadoTurno> estados);
    Optional<Turno> findByFechaAndHoraInicio(LocalDate fecha, LocalTime horaInicio);
    List<Turno> findByEstado(EstadoTurno estadoTurno);
    List<Turno> findByEstadoAndFechaGreaterThanEqualOrderByFechaAscHoraInicioAsc(EstadoTurno estado, LocalDate fecha);

}
