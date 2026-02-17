package com.example.patitas.Dtos;

public class TurnoAdminItemDto {
    private String nombrePerro;
    private String servicio;
    private String notas;
    private String tamanio;

    public String getNombrePerro() {
        return nombrePerro;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public void setNombrePerro(String nombrePerro) {
        this.nombrePerro = nombrePerro;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
