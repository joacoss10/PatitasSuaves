package com.example.patitas.Model;

import com.example.patitas.Model.Enums.DiaSemanaAgenda;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "diasAgenda",
        uniqueConstraints = @UniqueConstraint(name = "uk_dia_agenda_dia", columnNames = "dia_semana"))
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class DiaAgenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "diaSemana", nullable = false, length = 15)
    private DiaSemanaAgenda diaSemana;

    @Column(nullable = false)
    @Builder.Default
    private boolean habilitado = true;

    @OneToMany(mappedBy = "diaAgenda", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<RangoHorario> rangos = new ArrayList<>();


    public void addRango(RangoHorario rango) {
        rangos.add(rango);
        rango.setDiaAgenda(this);
    }

    public void removeRango(RangoHorario rango) {
        rangos.remove(rango);
        rango.setDiaAgenda(null);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDiaSemana(DiaSemanaAgenda diaSemana) {
        this.diaSemana = diaSemana;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public void setRangos(List<RangoHorario> rangos) {
        this.rangos = rangos;
    }

    public Long getId() {
        return id;
    }

    public DiaSemanaAgenda getDiaSemana() {
        return diaSemana;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public List<RangoHorario> getRangos() {
        return rangos;
    }
}
