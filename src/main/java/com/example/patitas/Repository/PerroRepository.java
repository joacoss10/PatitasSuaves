package com.example.patitas.Repository;

import com.example.patitas.Model.Perro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerroRepository extends JpaRepository<Perro, Long> {
    Optional<Perro> findByNombreAndCliente_id(String nombre, Long duenio);
    List<Perro>findByCliente_id(Long duenio);
    Optional<Perro>findByIdAndCliente_id(Long idPerro,Long idCliente);


}
