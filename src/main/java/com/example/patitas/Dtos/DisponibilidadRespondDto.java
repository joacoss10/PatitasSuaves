package com.example.patitas.Dtos;

import java.util.List;

public class DisponibilidadRespondDto {
    private String fecha;
    private List<String> horario;

    public String getFecha() {
        return fecha;
    }

    public List<String> getHorario() {
        return horario;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHorario(List<String> horario) {
        this.horario = horario;
    }
}
