package com.example.patitas.Repository;

import com.example.patitas.Model.RangoHorario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RangoHorarioRepository extends JpaRepository<RangoHorario, Long> {
}
