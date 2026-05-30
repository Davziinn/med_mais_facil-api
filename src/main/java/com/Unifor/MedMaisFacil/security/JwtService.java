package com.Unifor.MedMaisFacil.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secreteKey;

    @Value("${jwt.expiration}")
    private long expirationMs;


    // Gerou o TOKEN ----------------------------------------------------------
    public String gerarToken (UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("role", userDetails.getAuthorities()
                .iterator().next().getAuthority()
        );

        return buildToken(claims, userDetails.getUsername());
    }

    private String buildToken (Map<String, Object> claims, String email) {
        return Jwts.builder()
                .claims(claims)
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getSigningKey())
                .compact();
    }

    // Validar o TOKEN ----------------------------------------------------------
    public boolean isTokenValido (String token, UserDetails userDetails) {
        final String username = extrairUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpirado(token);

    }

    private boolean isTokenExpirado (String token) {
        return extrairExpiracao(token).before(new Date());
    }


    // Extrair dados do TOKEN ----------------------------------------------------------
    public String extrairUsername (String token) {
        return extrairClaim(token, Claims::getSubject);
    }

    private Date extrairExpiracao (String token) {
        return extrairClaim(token, Claims::getExpiration);
    }

    public <T> T extrairClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extrairTodasClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extrairTodasClaims (String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Pegar Chave de Assinatura ----------------------------------------------------------
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secreteKey.getBytes());
    }
}
