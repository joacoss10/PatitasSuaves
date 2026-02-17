package com.example.patitas.Service;

import com.example.patitas.Dtos.*;
import com.example.patitas.Model.Cliente;
import com.example.patitas.Model.Enums.Role;
import com.example.patitas.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.patitas.Security.JwtUtil;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private ClienteRepository repoCliente;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public CodigoRespondDto registrar(RegisterRequestDto dto) {
        Optional<Cliente> optionalCliente = repoCliente.findByEmailOrCelular(dto.getMail(), dto.getCelular());
        CodigoRespondDto respondDto = new CodigoRespondDto();



        if (optionalCliente.isEmpty()) {
            Cliente cliente = new Cliente();
            cliente.setNombre(dto.getNombre());
            cliente.setCelular(dto.getCelular());
            cliente.setEmail(dto.getMail());
            cliente.setPasswordHash(passwordEncoder.encode(dto.getContrasenia()));

            repoCliente.save(cliente);

            respondDto.setCodigo(2000);
            respondDto.setMensaje("Registro correcto");
        } else {
            Cliente c=optionalCliente.get();
            if(c.getEmail().equals(dto.getMail())){
            respondDto.setCodigo(5002);
            respondDto.setMensaje("Mail existente");
        }else {
                respondDto.setCodigo(5003);
                respondDto.setMensaje("Numero de Celular existente");
            }
        }

        return respondDto;
    }

    public CodigoRespondDto iniciarSesion(IncioSesionRequestDto dto) {
        CodigoRespondDto res = new CodigoRespondDto();

        Optional<Cliente> opt = repoCliente.findByEmail(dto.getMail());
        if (opt.isEmpty()) {
            res.setCodigo(5001);
            res.setMensaje("Credenciales incorrectas");
            return res;
        }

        Cliente c = opt.get();

        if (!passwordEncoder.matches(dto.getContrasenia(), c.getPasswordHash())) {
            res.setCodigo(5001);
            res.setMensaje("Credenciales incorrectas");
            return res;
        }

        String token = jwtUtil.generarToken(c.getId(),c.getEmail(), c.getRole().name());

        res.setCodigo(2001);
        res.setMensaje("Inicio exitoso");
        res.setToken(token);
        res.setUser(c.getId());
        return res;
    }
}