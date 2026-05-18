package com.example.patitas.Dtos;

import com.example.patitas.Model.Enums.EstadoTurno;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TurnoClienteRespondDto {
     private Double precio;
     private Long Id;
     private LocalTime hora;
     private LocalDate fecha;
     private List<TurnoItemClienteRespondDto> items;
     private EstadoTurno estado;

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<TurnoItemClienteRespondDto> getItems() {
        return items;
    }

    public void setItems(List<TurnoItemClienteRespondDto> items) {
        this.items = items;
    }

    public EstadoTurno getEstado() {
        return estado;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setEstado(EstadoTurno estado) {
        this.estado = estado;
    }
}
