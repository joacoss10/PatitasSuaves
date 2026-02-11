package com.example.patitas.Dtos;

import com.example.patitas.Model.Enums.DiaSemanaAgenda;
import lombok.Getter;
import lombok.Setter;

public class EstadoDiasRespondDto {
    private DiaSemanaAgenda dia;
    private boolean estado;
    private Long id;

    public void setDia(DiaSemanaAgenda dia) {
        this.dia = dia;
    }

    public DiaSemanaAgenda getDia() {
        return dia;
    }

    public boolean isEstado() {
        return estado;
    }

    public Long getId() {
        return id;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
