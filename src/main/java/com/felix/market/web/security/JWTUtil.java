package com.felix.market.web.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtil {

    SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private final int SECONDS = 60;
    private final int MINUTES = 60;
    private final int HOURS = 10;

    public String generateToken(UserDetails userDetails) {

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * SECONDS * MINUTES * HOURS))
                .signWith(key).compact();
    }
}
