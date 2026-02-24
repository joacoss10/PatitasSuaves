package com.example.patitas.Service;

import com.example.patitas.Dtos.*;
import com.example.patitas.Exeptions.ApiException;
import com.example.patitas.Model.Cliente;
import com.example.patitas.Repository.ClienteRepository;
import com.example.patitas.Util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Autowired
    private SecurityUtils securityUtils;

    public void registrar(RegisterRequestDto dto) {
        Optional<Cliente> optionalCliente = repoCliente.findByEmailOrCelular(dto.getMail(), dto.getCelular());
        if (optionalCliente.isEmpty()) {
            Cliente cliente = new Cliente();
            cliente.setNombre(dto.getNombre());
            cliente.setCelular(dto.getCelular());
            cliente.setEmail(dto.getMail());
            cliente.setPasswordHash(passwordEncoder.encode(dto.getContrasenia()));
            repoCliente.save(cliente);

        } else {
            Cliente c=optionalCliente.get();
            if(c.getEmail().equals(dto.getMail())){
            throw new ApiException("Mail existente",HttpStatus.CONFLICT);
        }else {
                throw new ApiException("Celular existente",HttpStatus.CONFLICT);
            }
        }
    }

    public TokenRespondDto iniciarSesion(IncioSesionRequestDto dto) {
        TokenRespondDto res = new TokenRespondDto();

        Optional<Cliente> opt = repoCliente.findByEmail(dto.getMail());
        if (opt.isEmpty()) {
            throw new ApiException("Credenciales incorrectas", HttpStatus.BAD_REQUEST);
        }

        Cliente c = opt.get();

        if (!passwordEncoder.matches(dto.getContrasenia(), c.getPasswordHash())) {
            throw new ApiException("Credenciales incorrectas", HttpStatus.BAD_REQUEST);
        }

        String token = jwtUtil.generarToken(c.getId(),c.getEmail(), c.getRole().name());
        res.setToken(token);
        return res;
    }
    public void cambiarContrasenia(OlvidoDeContraseniaRequest request){
        String email= securityUtils.getMail();
        Optional<Cliente> clienteOptional=repoCliente.findByEmail(email);
        if(clienteOptional.isPresent()){
            clienteOptional.get().setPasswordHash(passwordEncoder.encode(request.getContrasenia()));
            repoCliente.save(clienteOptional.get());
        }else throw new ApiException("Mail incorrecto", HttpStatus.BAD_REQUEST);
    }
}