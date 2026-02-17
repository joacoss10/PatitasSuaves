package com.example.patitas.Dtos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class GenerarTurnoDto {
    private LocalDate fecha;
    private LocalTime horaIncio;
    private Long idCliente;
    private List<TurnoItemDto> items;

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraIncio() {
        return horaIncio;
    }

    public void setHoraIncio(LocalTime horaIncio) {
        this.horaIncio = horaIncio;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public List<TurnoItemDto> getItems() {
        return items;
    }

    public void setItems(List<TurnoItemDto> items) {
        this.items = items;
    }
}
