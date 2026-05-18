package com.example.patitas.Security;

public class UsuarioAutenticado {

    private final Long clienteId;
    private final String username;

    public UsuarioAutenticado(Long clienteId, String username) {
        this.clienteId = clienteId;
        this.username = username;

    }

    public Long getClienteId() {
        return clienteId;
    }

    public String getUsername() {
        return username;
    }


}
