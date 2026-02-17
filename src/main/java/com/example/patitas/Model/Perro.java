package com.example.patitas.Model;

import com.example.patitas.Model.Enums.TamanioPerro;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "perros")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Perro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TamanioPerro tamanio;

    @Column(length = 600)
    private String observaciones;
    @Column(nullable = false)
    private boolean activo = true;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_perro_cliente"))
    private Cliente cliente;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTamanio(TamanioPerro tamanio) {
        this.tamanio = tamanio;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }

    public TamanioPerro getTamanio() {
        return tamanio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

