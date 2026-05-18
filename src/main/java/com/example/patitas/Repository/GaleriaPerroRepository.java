package com.example.patitas.Repository;

import com.example.patitas.Model.GaleriaPerro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GaleriaPerroRepository extends JpaRepository<GaleriaPerro, Long> {
}