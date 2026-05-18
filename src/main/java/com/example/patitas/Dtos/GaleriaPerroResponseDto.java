package com.example.patitas.Dtos;

public class GaleriaPerroResponseDto {
    private Long id;
    private String nombrePerro;
    private String servicioRealizado;
    private String comentarios;
    private String fotoUrl; // URL para que el front ponga en el <img src="" />

    // Constructor, Getters y Setters
    public GaleriaPerroResponseDto(Long id, String nombrePerro, String servicioRealizado, String comentarios, String fotoUrl) {
        this.id = id;
        this.nombrePerro = nombrePerro;
        this.servicioRealizado = servicioRealizado;
        this.comentarios = comentarios;
        this.fotoUrl = fotoUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombrePerro(String nombrePerro) {
        this.nombrePerro = nombrePerro;
    }

    public void setServicioRealizado(String servicioRealizado) {
        this.servicioRealizado = servicioRealizado;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public Long getId() {
        return id;
    }

    public String getNombrePerro() {
        return nombrePerro;
    }

    public String getServicioRealizado() {
        return servicioRealizado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }
}