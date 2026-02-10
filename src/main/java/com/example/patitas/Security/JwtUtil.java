package com.example.patitas.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final String claveSecreta="JedsR0sSeCod@wnsaAJedklsGodkaIO123";
    private final long expiration = 1000L * 60 * 60 * 24 * 7;

    private Key getSingInKey(){
        return Keys.hmacShaKeyFor(claveSecreta.getBytes());
    }

    public String generarToken(String alias, String rol) {
        return Jwts.builder()
                .setSubject(alias)
                .claim("rol", rol)
                .setIssuedAt(new Date())
                .signWith(getSingInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUserName(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSingInKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public String getRol(String token) { //OBTENER ROL
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSingInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("rol", String.class);
    }
    public boolean validarToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(getSingInKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
