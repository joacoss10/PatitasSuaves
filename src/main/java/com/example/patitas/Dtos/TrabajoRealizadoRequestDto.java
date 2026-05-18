package com.example.patitas.Dtos;

import org.springframework.web.multipart.MultipartFile;

public class TrabajoRealizadoRequestDto {
    private String nombrePerro;
    private String servicio;
    private String descripcion;
    private MultipartFile imagen;

    public String getNombrePerro() {

        return nombrePerro;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public MultipartFile getImagen() {
        return imagen;
    }

    public void setImagen(MultipartFile imagen) {
        this.imagen = imagen;
    }
}
