package com.example.patitas.Repository;

import com.example.patitas.Model.DiaAgenda;
import com.example.patitas.Model.Enums.DiaSemanaAgenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiaAgendaRepository extends JpaRepository<DiaAgenda, Long> {
    Optional<DiaAgenda> findByDiaSemana(DiaSemanaAgenda diaSemana);
    List<DiaAgenda> findByHabilitadoTrue();

    boolean existsByDiaSemana(DiaSemanaAgenda diaSemana);
}
