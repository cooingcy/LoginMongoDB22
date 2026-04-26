package io.github.mongo.login.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtSecurity {
    private static final String SECRET = "p8GvZk3Xr9Qm2TnL7wYcB5fH1UaE6sD4Jx0PqR8MvCzN9KjVbL2WgT7hF3SyA1eU";
    private static final long EXP = 2L * 600 * 1000;

    private byte[] getSecret() {
        return SECRET.getBytes();
    }

    public String generateToken(UserDetails user) {
        Date date = new Date();
        SecretKey key = Keys.hmacShaKeyFor(getSecret());
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("roles", user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .issuedAt(date)
                .expiration(new Date(date.getTime() + EXP))
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    public String getUsername(String token) {
        return parseClaims(token).getSubject();
    }

    public boolean isExpired(String token) {
        return parseClaims(token).getExpiration().before(new Date());
    }

    public boolean isTokenValid(String token, UserDetails user) {
        String username = getUsername(token);
        return username.equals(user.getUsername()) && !isExpired(token);
    }

    private Claims parseClaims(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(getSecret());
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
