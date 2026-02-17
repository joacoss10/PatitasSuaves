package com.example.patitas.Repository;

import com.example.patitas.Model.Enums.EstadoTurno;
import com.example.patitas.Model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    List<Turno> findByFechaBetweenAndEstadoIn(LocalDate desde, LocalDate hasta, Collection<EstadoTurno> estados);

    boolean existsByFechaAndHoraInicioAndEstadoIn(
            LocalDate fecha,
            LocalTime horaInicio,
            List<EstadoTurno> estados
    );

    List<Turno> findByEstado(EstadoTurno estadoTurno);

    List<Turno> findByEstadoAndFechaGreaterThanEqualOrderByFechaAscHoraInicioAsc(EstadoTurno estado, LocalDate fecha);

    List<Turno> findByClienteIdAndEstado(Long idCliente, EstadoTurno estado);

    Optional<Turno> findByClienteIdAndId(Long idCliente, Long idTurno);

    @Modifying
    @Query(value = """
                UPDATE turnos
                SET estado = 'Realizado'
                WHERE estado = 'Confirmado'
                AND (
                    (fecha < :hoy)
                    OR
                    (fecha = :hoy 
                     AND (hora_inicio + INTERVAL '90 minutes') <= :horaActual)
                )
            """, nativeQuery = true)
    int marcarTurnosComoRealizados(
            @Param("hoy") LocalDate hoy,
            @Param("horaActual") LocalTime horaActual
    );
}
