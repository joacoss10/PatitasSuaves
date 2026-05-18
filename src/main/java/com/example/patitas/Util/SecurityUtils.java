package com.example.patitas.Util;

import com.example.patitas.Security.UsuarioAutenticado;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
    public Long getClienteId() {
        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
        UsuarioAutenticado usuario =(UsuarioAutenticado) authentication.getPrincipal();
        return usuario.getClienteId();
    }
    public String getMail(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        UsuarioAutenticado usuarioAutenticado=(UsuarioAutenticado) authentication.getPrincipal();
        return usuarioAutenticado.getUsername();
    }

}
