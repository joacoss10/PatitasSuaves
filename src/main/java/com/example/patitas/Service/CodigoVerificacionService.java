package com.example.patitas.Service;

import com.example.patitas.Dtos.TokenRespondDto;
import com.example.patitas.Dtos.OlvidoDeContraseniaRequest;
import com.example.patitas.Exeptions.ApiException;
import com.example.patitas.Model.CodigoVerificacion;
import com.example.patitas.Repository.CodigoVerificacionRepository;
import com.example.patitas.Security.JwtUtil;
import com.example.patitas.Util.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class CodigoVerificacionService {
    @Autowired
    private CodigoVerificacionRepository codigoVerificacionRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private JwtUtil jwtUtil;


    public void restablecerContraseniaRequest(OlvidoDeContraseniaRequest request){
        if(clienteService.existePorMail(request.getMail())){
            String code=generateCode();
            String hashed = passwordEncoder.encode(code);
            CodigoVerificacion codigoVerificacion = new CodigoVerificacion();
            codigoVerificacion.setEmail(request.getMail());
            codigoVerificacion.setCodeHash(hashed);
            codigoVerificacion.setExpiration(LocalDateTime.now().plusMinutes(10));
            codigoVerificacion.setType("PasswordReset");
            codigoVerificacion.setUsed(false);
            codigoVerificacionRepository.save(codigoVerificacion);
            emailSender.enviarCodigoRecupero(request.getMail(),code);
        }else{
            throw new ApiException("Mail no encontrado", HttpStatus.NOT_FOUND);
        }



    }
    public TokenRespondDto verificarCodigo(OlvidoDeContraseniaRequest request){
        TokenRespondDto respondDto=new TokenRespondDto();
        Optional<CodigoVerificacion> verificacionOpt=codigoVerificacionRepository.findTopByEmailAndTypeAndUsedFalseOrderByExpirationDesc(request.getMail(),"PasswordReset");
        if(verificacionOpt.isPresent()){
            CodigoVerificacion verificacion=verificacionOpt.get();
            if(verificacion.getExpiration().isBefore(LocalDateTime.now())){
                throw new ApiException("Codigo inválido o vencido, intente nuevamente", HttpStatus.GONE);
            }
            if(!passwordEncoder.matches(request.getCodigo(),verificacion.getCodeHash())){
                throw new ApiException("Codigo inválido o vencido, intente nuevamente", HttpStatus.GONE);
            }
            verificacion.setUsed(true);
            codigoVerificacionRepository.save(verificacion);
            respondDto.setToken(jwtUtil.generarTokeTemporal(request.getMail()));

        }else  throw new ApiException("Codigo no encontrado, intente nuevamente", HttpStatus.NOT_FOUND);
        return respondDto;
    }

























    private String generateCode() {
        SecureRandom random = new SecureRandom();
        int number = 100000 + random.nextInt(900000);
        return String.valueOf(number);
    }


}
