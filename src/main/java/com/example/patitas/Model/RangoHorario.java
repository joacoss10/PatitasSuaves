package com.example.patitas.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "rangoHorarios",
        indexes = @Index(name = "idx_rango_dia_agenda", columnList = "dia_agenda_id"))
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class RangoHorario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "dia_agenda_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_rango_dia_agenda")
    )
    private DiaAgenda diaAgenda;

    @Column(nullable = false)
    private LocalTime horaInicio;

    @Column(nullable = false)
    private LocalTime horaFin;


    @Column(nullable = false)
    private int duracion;

    @Column(nullable = false)
    @Builder.Default
    private boolean habilitado = true;

    public void setId(Long id) {
        this.id = id;
    }

    public void setDiaAgenda(DiaAgenda diaAgenda) {
        this.diaAgenda = diaAgenda;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Long getId() {
        return id;
    }

    public DiaAgenda getDiaAgenda() {
        return diaAgenda;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public int getDuracion() {
        return duracion;
    }

    public boolean isHabilitado() {
        return habilitado;
    }
}


