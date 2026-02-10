package com.example.patitas.Dtos;

public class EditarPreciosServicioRequestDto {
    private Long idServicio;
    private Double precio;

    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Long getIdServicio() {
        return idServicio;
    }

    public Double getPrecio() {
        return precio;
    }
}
