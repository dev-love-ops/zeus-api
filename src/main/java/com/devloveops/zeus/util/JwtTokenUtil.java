package com.devloveops.zeus.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author RockyWu
 * @date 2019-01-19 22:57
 */
@Component
public class JwtTokenUtil {


    @Value("${jwt.secret}")
    private String secret;


    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                    .parseClaimsJws(token).getBody();

        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
    /**
     * 生成Token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(this.generateExpirationDate())
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    private Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        System.out.println(expiration);
        return expiration;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        return true;
//        JwtUser user = (JwtUser) userDetails;
//        final String username = getUsernameFromToken(token);
//        //final Date expiration = getExpirationDateFromToken(token);
//        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    public Boolean validateToken(String token){
        try {

            Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8))).parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * token 过期时间, 默认30天
     * @return
     */
    private Date generateExpirationDate() {
        long expiration = 30 * 24 * 60 * 60 * 1000L;
        return new Date(System.currentTimeMillis() + expiration);
    }

}
