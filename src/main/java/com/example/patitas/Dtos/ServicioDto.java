package com.example.patitas.Dtos;

public class ServicioDto {
    private Long id;
    private String servicio;
    private double precio;
    private boolean activo;

    public Long getId() {
        return id;
    }

    public String getServicio() {
        return servicio;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
