package com.example.patitas.Dtos;

public class NuevoServicioRequestDto {
    private String nombreServicio;
    private Double precioServicio;

    public String getNombreServicio() {
        return nombreServicio;
    }

    public Double getPrecioServicio() {
        return precioServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public void setPrecioServicio(Double precioServicio) {
        this.precioServicio = precioServicio;
    }
}
