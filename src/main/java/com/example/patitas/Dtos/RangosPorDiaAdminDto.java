package com.example.patitas.Dtos;

import com.example.patitas.Model.Enums.DiaSemanaAgenda;

import java.time.LocalTime;
import java.util.List;

public class RangosPorDiaAdminDto {
    private Long id;
    private DiaSemanaAgenda dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private boolean habilitado;
    private List<String> turnosGenerados;

    public DiaSemanaAgenda getDia() {
        return dia;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public List<String> getTurnosGenerados() {
        return turnosGenerados;
    }

    public void setDia(DiaSemanaAgenda dia) {
        this.dia = dia;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public void setTurnosGenerados(List<String> turnosGenerados) {
        this.turnosGenerados = turnosGenerados;
    }
}
