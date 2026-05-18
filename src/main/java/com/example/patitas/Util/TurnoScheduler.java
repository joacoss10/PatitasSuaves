package com.example.patitas.Util;

import com.example.patitas.Repository.TurnoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

@EnableScheduling
@Service
public class TurnoScheduler {
    @Autowired
    private TurnoRepository turnoRepository;
    private static final ZoneId ZONE_AR = ZoneId.of("America/Argentina/Buenos_Aires");
    @Scheduled(fixedRate = 900000)
    @Transactional
    public void limpiezaTurnos() {


        LocalDate hoy = LocalDate.now(ZONE_AR);
        LocalTime ahora = LocalTime.now(ZONE_AR);

        turnoRepository.marcarTurnosComoRealizados(hoy, ahora);
        turnoRepository.marcarTurnoRechazado(hoy,ahora);
    }
}

