package com.example.patitas.Repository;

import com.example.patitas.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    Optional<Cliente> findByEmailOrCelular(String mail, String celuar);
    Optional<Cliente> findByEmail(String mail);

}
