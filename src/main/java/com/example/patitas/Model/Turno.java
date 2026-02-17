package com.example.patitas.Model;

import com.example.patitas.Model.Enums.EstadoTurno;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "turnos"
)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_turno_cliente"))
    private Cliente cliente;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private EstadoTurno estado = EstadoTurno.AConfirmar;


    private Double total;

    @OneToMany(mappedBy = "turno", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<TurnoItem> items = new ArrayList<>();


    public void addItem(TurnoItem item) {
        items.add(item);
        item.setTurno(this);
    }

    public void removeItem(TurnoItem item) {
        items.remove(item);
        item.setTurno(null);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public EstadoTurno getEstado() {
        return estado;
    }

    public Double getTotal() {
        return total;
    }

    public List<TurnoItem> getItems() {
        return items;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setEstado(EstadoTurno estado) {
        this.estado = estado;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setItems(List<TurnoItem> items) {
        this.items = items;
    }
}
