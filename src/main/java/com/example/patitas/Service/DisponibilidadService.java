package com.example.patitas.Service;

import com.example.patitas.Repository.RangoHorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisponibilidadService {
    @Autowired
    RangoHorarioRepository repository;
    @Autowired
    DiaAgendaService diaAgendaService;

    public void crearNuevaDisponibilidad
}
