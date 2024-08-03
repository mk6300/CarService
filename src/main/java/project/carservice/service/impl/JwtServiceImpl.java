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

    private final String secretKey;

    private final long validityInMilliseconds;

    public JwtServiceImpl(@Value("${jwt.secret}") String secretKey, @Value("${jwt.expiration}") long validityInMilliseconds) {
        this.secretKey = secretKey;
        this.validityInMilliseconds = validityInMilliseconds;
    }


    @Override
    public String generateToken(String userId, Map<String, Object> claims) {
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userId)
                .setIssuedAt(now)
                .setNotBefore(now)
                .setExpiration(new Date(now.getTime() + validityInMilliseconds))
                .signWith(GetSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key GetSecretKey() {
        byte[] decodedKey = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(decodedKey);
    }
}
