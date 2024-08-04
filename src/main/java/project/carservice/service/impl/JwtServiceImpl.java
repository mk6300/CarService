package project.carservice.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import project.carservice.service.JwtService;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    private final String jwtSecret;

    private final long expiration;

    public JwtServiceImpl(@Value("${jwt.secret}") String jwtSecret,
                          @Value("${jwt.expiration}") long expiration) {
        this.jwtSecret = jwtSecret;
        this.expiration = expiration;
    }


    @Override
    public String generateToken(String userId, Map<String, Object> claims) {
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userId)
                .setIssuedAt(now)
                .setNotBefore(now)
                .setExpiration(new Date(now.getTime() + expiration))
                .signWith(GetSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key GetSecretKey() {
        byte[] decodedKey = jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(decodedKey);
    }
}
