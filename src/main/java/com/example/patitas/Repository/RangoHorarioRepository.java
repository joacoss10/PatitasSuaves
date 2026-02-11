package com.example.patitas.Repository;

import com.example.patitas.Model.DiaAgenda;
import com.example.patitas.Model.RangoHorario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RangoHorarioRepository extends JpaRepository<RangoHorario, Long> {
    List<RangoHorario> findByDiaAgendaAndHabilitadoTrue(DiaAgenda diaAgenda);
    List<RangoHorario> findByDiaAgendaId(Long diaAgendaId);
}
