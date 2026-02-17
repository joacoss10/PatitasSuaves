package com.example.patitas.Service;

import com.example.patitas.Dtos.DatosClienteRespondDto;
import com.example.patitas.Dtos.ModificacionDatosClienteRequestDto;
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
    public DatosClienteRespondDto obtenerDatosCliente(Long id){
        DatosClienteRespondDto respond=new DatosClienteRespondDto();
        Optional<Cliente> clienteOptional=repository.findById(id);
        if(clienteOptional.isPresent()){
            respond.setMail(clienteOptional.get().getEmail());
            respond.setNombre(clienteOptional.get().getNombre());
            respond.setTelefono(clienteOptional.get().getCelular());
        }
        return respond;
    }
    public void ModificarDatosCliente(ModificacionDatosClienteRequestDto request){
        Optional<Cliente> clienteOptional=repository.findById(request.getIdCliente());
        if(clienteOptional.isPresent()){
            if(!request.getNombre().isEmpty()){
                clienteOptional.get().setNombre(request.getNombre());
            }else if(!request.getTelefono().isEmpty()){
                clienteOptional.get().setCelular(request.getTelefono());
            }
            repository.save(clienteOptional.get());
        }
    }



}
