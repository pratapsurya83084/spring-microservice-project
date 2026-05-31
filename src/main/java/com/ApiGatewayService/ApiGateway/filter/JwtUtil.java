package com.ApiGatewayService.ApiGateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;

@Component
public class JwtUtil {
    private static final String SECRET_KEY =
            "bXlzZWNyZXRrZXlteXNlY3JldGtleW15c2VjcmV0a2V5MTI=";



    public SecretKey getKey() {

        byte[] keyBytes =
                Base64.getDecoder().decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(keyBytes);
    }


    public Jws<Claims> validateToken(String token){
      return    Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build()
                .parseSignedClaims(token);

    }



}
