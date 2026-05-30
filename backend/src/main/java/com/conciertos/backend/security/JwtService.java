package com.conciertos.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private static final String SECRET_KEY = "clave_super_secreta_conciertos_123456789012345678901234567890";
    // mínimo 32 caracteres para HS256

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generarToken(String correo,String rol) {

        Map<String,Object> claims =
                new HashMap<>();

        claims.put("rol", rol);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(correo)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60))
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
    }

    public String extraerCorreo(String token) {
        return extraerClaim(token, Claims::getSubject);
    }

    public <T> T extraerClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extraerTodosClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extraerTodosClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key) // ✅ usa la Key, no el String
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validarToken(String token, String correo) {
        final String correoToken = extraerCorreo(token);
        return correoToken.equals(correo);
    }
}
