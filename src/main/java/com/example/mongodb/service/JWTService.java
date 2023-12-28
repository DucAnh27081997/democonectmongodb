package com.example.mongodb.service;

import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Service
@Log4j2
public class JWTService {

    @Value("${app.jwtSecret}")
    private static String jwtSecret;

    @Value("${app.jwtExpirationMs}")
    private static int jwtExpirationMs;

    @Value("${app.jwtRefreshExpirationMs}")
    private static int jwtRefreshExpirationMs;


    public static String generateJwtToken(Authentication authentication) {
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public static String generateJwtToken(Authentication authentication, Map<String, Object> claim) {
        return generateJwtToken(authentication.getName(), claim);
    }

    public static String generateJwtToken(String name, Map<String, Object> claim) {
        Date now = new Date();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .addClaims(claim)
                .setSubject(name)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public static String generateJwtToken(Key key, String name) {
        Date now = new Date();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(name)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
    public static String generateJwtToken(String strkey, String name) {
        Date now = new Date();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(name)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, strkey)
                .compact();
    }

    private static Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    public static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public static Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    //check if the token has expired
    private static Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public static boolean validateJwtToken(String authToken) {
        try {
            Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(jwtSecret),
                    SignatureAlgorithm.HS256.getJcaName());
            if (isTokenExpired(authToken)) {
                Jwts.parser()
                        .setSigningKey(hmacKey)
                        .parseClaimsJws(authToken);
                return true;
            }
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    private static SecretKey convertKey(String base64Secret) {
        return new SecretKeySpec(Base64.getDecoder().decode(base64Secret), SignatureAlgorithm.HS256.getJcaName());
    }

    public static void main(String[] args) {

        //String jwtSecret = "2EJR4zS41QuRT9M_VI5egnPQ9UFXEG7xfdktdIIbRlE";
        String encode = "S2V5LU11c3QtQmUtYXQtbGVhc3QtMzItYnl0ZXMtaW4tbGVuZ3RoIQ==";

        Key hmacKey = new SecretKeySpec(encode.getBytes(),
                SignatureAlgorithm.HS256.getJcaName());

        System.out.println("decode: " + new String(Base64.getDecoder().decode(encode)));
        System.out.println("byte length: " + (encode.getBytes().length));
        System.out.println("Key: " + new String(hmacKey.getFormat()));
        try {

            String strJWT = generateJwtToken(hmacKey, "ducla");
            System.out.println(strJWT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

//    public static void main(String[] args) throws NoSuchAlgorithmException {
//        System.out.printf(convertKey("asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4").getAlgorithm());
//    }
}
