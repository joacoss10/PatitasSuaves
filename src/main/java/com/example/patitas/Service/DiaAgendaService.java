package com.example.patitas.Service;

import com.example.patitas.Dtos.EstadoDiasRespondDto;
import com.example.patitas.Model.DiaAgenda;
import com.example.patitas.Model.Enums.DiaSemanaAgenda;
import com.example.patitas.Repository.DiaAgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiaAgendaService {
    @Autowired
    private DiaAgendaRepository repository;
    public void CambiarDisponibilidad(Long iDdia){
         Optional<DiaAgenda> diaSemana= repository.findById(iDdia);
         if (diaSemana.isPresent()){
             if(diaSemana.get().isHabilitado()){
                 diaSemana.get().setHabilitado(false);
             }else{diaSemana.get().setHabilitado(true);}
             repository.save(diaSemana.get());
         }
    }
    public Optional<DiaAgenda> encontrarDia (Long idDia){
        return repository.findById(idDia);
    }
    public Optional<DiaAgenda> encontrarDia(DiaSemanaAgenda dia){
        return repository.findByDiaSemana(dia);
    }

    public List<EstadoDiasRespondDto> obtenerDias(){
        List<EstadoDiasRespondDto> respond=new ArrayList<>();
        List<DiaAgenda> listDias= repository.findAll();
        for(DiaAgenda aux:listDias){
            EstadoDiasRespondDto responAux=new EstadoDiasRespondDto();
            responAux.setDia(aux.getDiaSemana());
            responAux.setId(aux.getId());
            responAux.setEstado(aux.isHabilitado());
            respond.add(responAux);
        }
        return respond;
    }

}
