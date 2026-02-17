package com.example.patitas.Dtos;

public class ModificacionDatosClienteRequestDto {

    private String telefono;
    private String nombre;

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
