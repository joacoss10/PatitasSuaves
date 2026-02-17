package com.example.patitas.Dtos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class GenerarTurnoRequestDto {
    private LocalDate fecha;
    private LocalTime horaInicio;
    private List<GenerarTurnoItemRequestDto> items;

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }


    public List<GenerarTurnoItemRequestDto> getItems() {
        return items;
    }

    public void setItems(List<GenerarTurnoItemRequestDto> items) {
        this.items = items;
    }
}
