package com.nixon.BePrepared.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JWTService {

    private static final String SECRET_KEY = "mgkavmqnK2SkZkXvUrdBVCiSUn9Kvd5ihdsjzkcnknre43nkfnkkcnsdjnj21j32h4h4ADBHA90g";
    private static final long JWT_EXPIRATION = 86400000;

    public String extractUserFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token){
        return (extractExpiration(token).before(new Date()));
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUserFromToken(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object > extraClaims, UserDetails userDetails){
        return buildToken(extraClaims, userDetails, JWT_EXPIRATION);
    }

    private String buildToken(Map<String, Object > extraClaims, UserDetails userDetails, long expiration){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractAllClaimsFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
