package com.example.patitas.Util;

import com.example.patitas.Model.DiaAgenda;
import com.example.patitas.Model.Enums.DiaSemanaAgenda;
import com.example.patitas.Repository.DiaAgendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AgendaSeeder implements CommandLineRunner {
    @Autowired
    private DiaAgendaRepository diaAgendaRepository;

    @Override
    public void run(String... args) {

        for (DiaSemanaAgenda dia : DiaSemanaAgenda.values()) {

            if (!diaAgendaRepository.existsByDiaSemana(dia)) {
                DiaAgenda diaAgenda =new DiaAgenda();
                        diaAgenda.setDiaSemana(dia);diaAgenda.setHabilitado(true);


                diaAgendaRepository.save(diaAgenda);
            }
        }
    }
}
