package com.example.patitas.Dtos;

public class RegisterRequestDto {

    private String nombre;
    private String mail;
    private String contrasenia;
    private String celular;

    public String getNombre() {
        return nombre;
    }

    public String getMail() {
        return mail;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getCelular() {
        return celular;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
