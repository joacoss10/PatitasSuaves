package com.example.patitas.Dtos;

import com.example.patitas.Model.Enums.TamanioPerro;

public class RegistroPerroRequestDto {
    private Long idCliente;
    private String nombre;
    private String observaciones;
    private TamanioPerro tamanioPerro;

    public String getNombre() {
        return nombre;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public TamanioPerro getTamanioPerro() {
        return tamanioPerro;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setTamanioPerro(TamanioPerro tamanioPerro) {
        this.tamanioPerro = tamanioPerro;
    }
}
