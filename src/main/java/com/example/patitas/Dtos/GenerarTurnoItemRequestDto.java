package com.example.patitas.Dtos;

public class GenerarTurnoItemRequestDto {
    private Long perroId;
    private Long servicioId;

    public Long getPerroId() {
        return perroId;
    }

    public Long getServicioId() {
        return servicioId;
    }

    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
    }

    public void setPerroId(Long perroId) {
        this.perroId = perroId;
    }
}
