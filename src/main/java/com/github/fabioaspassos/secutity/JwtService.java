package com.github.fabioaspassos.secutity;

import com.github.fabioaspassos.entity.Usuario;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class JwtService {

    @Value("${security.jwt.expiration}")
    private String expiration;

    @Value("${security.jwt.privateKey}")
    private String privateKey;

    public String getToken(Usuario usuario){
        long minutes = Long.parseLong(expiration);
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(minutes);
        Instant instant = expirationTime.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);

        return Jwts
                .builder()
                .setSubject(usuario.getLogin())
                .setExpiration(data)
                .signWith(SignatureAlgorithm.HS512, privateKey)
                .compact();
    }

    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(privateKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token){
        try {
            Claims claims = getClaims(token);
            Date expData = claims.getExpiration();
            LocalDateTime expToken = expData.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(expToken);
        } catch (Exception e){
            return false;
        }
    }

    public String getLoginUsuario(String token) throws ExpiredJwtException{
        return getClaims(token).getSubject();
    }
}
