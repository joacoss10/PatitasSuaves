package com.example.patitas.Service;

import com.example.patitas.Model.DiaAgenda;
import com.example.patitas.Model.Enums.DiaSemanaAgenda;
import com.example.patitas.Repository.DiaAgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiaAgendaService {
    @Autowired
    DiaAgendaRepository repository;
    public void CambiarDisponibilidad(DiaSemanaAgenda dia){
         Optional<DiaAgenda> diaSemana= repository.findByDiaSemana(dia);
         if (diaSemana.isPresent()){
             if(diaSemana.get().isHabilitado()){
                 diaSemana.get().setHabilitado(false);
             }else{diaSemana.get().setHabilitado(true);}
             repository.save(diaSemana.get());
         }
    }
    public Long idDia(DiaSemanaAgenda dia){
        Optional<DiaAgenda> diaSemana= repository.findByDiaSemana(dia);
        if(diaSemana.isPresent()){
            return diaSemana.get().getId();
        }
        return -1L;
    }
}
