package com.example.patitas.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "turno_items",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_turno_perro", columnNames = {"turno_id", "perro_id"})
        }
)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class TurnoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "turno_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_item_turno"))
    private Turno turno;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "perro_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_item_perro"))
    private Perro perro;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "servicio_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_item_servicio"))
    private Servicio servicio;

    private Double precio;

    @Column(length = 300)
    private String notas;

    public Long getId() {
        return id;
    }

    public Turno getTurno() {
        return turno;
    }

    public Perro getPerro() {
        return perro;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public Double getPrecio() {
        return precio;
    }

    public String getNotas() {
        return notas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public void setPerro(Perro perro) {
        this.perro = perro;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}

