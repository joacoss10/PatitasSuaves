package com.example.patitas.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {
    @Autowired
    private JavaMailSender mailSender;
    @Async
    public void enviarCodigoRecupero(String mail, String codigo){
        SimpleMailMessage mensaje=new SimpleMailMessage();
        mensaje.setTo(mail);
        mensaje.setSubject("CODIGO DE RECUPERACIÓN DE CONTRASEÑA");
        mensaje.setText("Hola, su codigo de recuperacion es: "+codigo+"\n Este codigo expira en 10 minutos.");
        mailSender.send(mensaje);
    }


}
