package com.timemanage.gigatomson.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.access-token-valid-seconds}")
    private long accessTokenValidSeconds;

    public String createAccessToken(Long userId) {
        var key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        var now = Instant.now();
        var exp = now.plusSeconds(accessTokenValidSeconds);

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuer(issuer)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(exp))
                .signWith(key)
                .compact();
    }
}