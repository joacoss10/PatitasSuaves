package com.example.patitas.Service;

import com.example.patitas.Model.Cliente;
import com.example.patitas.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public Optional<Cliente> encontrarCliente(Long id){
      return repository.findById(id);
    }




}
