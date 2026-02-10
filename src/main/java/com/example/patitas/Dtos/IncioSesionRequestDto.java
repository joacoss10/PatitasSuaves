package com.example.patitas.Dtos;

public class IncioSesionRequestDto {


    private String mail;
    private String contrasenia;

    public String getMail() {
        return mail;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
