package com.example.patitas.Dtos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TurnoAdminDto {
    private LocalDate dia;
    private LocalTime hora;
    private Long id;
    private List<TurnoAdminItemDto> item;
    private Double total;
    private String nombreCliente;
    private String celularCliente;

    public String getCelularCliente() {
        return celularCliente;
    }

    public void setCelularCliente(String celularCliente) {
        this.celularCliente = celularCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TurnoAdminItemDto> getItem() {
        return item;
    }

    public void setItem(List<TurnoAdminItemDto> item) {
        this.item = item;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
