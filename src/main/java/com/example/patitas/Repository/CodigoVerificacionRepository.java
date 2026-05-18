package com.example.patitas.Repository;

import com.example.patitas.Model.CodigoVerificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CodigoVerificacionRepository extends JpaRepository<CodigoVerificacion, Long> {
    Optional<CodigoVerificacion> findTopByEmailAndTypeAndUsedFalseOrderByExpirationDesc(String email, String type);
}
