package com.example.patitas.Model;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarbinaryJdbcType;

@Entity
@Table(name = "galeria_perros")
public class GaleriaPerro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_perro", nullable = false)
    private String nombrePerro;

    @Column(name = "servicio_realizado", nullable = false)
    private String servicioRealizado;

    private String comentarios;

    @JdbcType(VarbinaryJdbcType.class)
    @Column(name = "foto")
    private byte[] foto;

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

    public byte[] getFoto() {
        return foto;
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

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}