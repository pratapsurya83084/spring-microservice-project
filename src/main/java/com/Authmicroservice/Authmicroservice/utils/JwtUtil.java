package com.Authmicroservice.Authmicroservice.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private static final String SECRET_KEY =
            "bXlzZWNyZXRrZXlteXNlY3JldGtleW15c2VjcmV0a2V5MTI=";

    // generate  JwtToken
    public String generateToken(String Username){

        Map<String ,Object> claim = new HashMap<>();
        claim.put("email","pratap@gmail.com");

      String token = Jwts.builder()
                .subject(Username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5))
                .claims(claim)  // include email pair into token
                .signWith(getKey())
                .compact();

        return token;
    }


//    public Key getkey(){
//        byte[] bytes = Base64.getDecoder().decode(secretkey);
//        return Keys.hmacShaKeyFor(bytes); //here automatically genrate using byte length , no need to use algorithms like SHA256,etc.
//
//    }
public SecretKey getKey() {

    byte[] keyBytes =
            Base64.getDecoder().decode(SECRET_KEY);

    return Keys.hmacShaKeyFor(keyBytes);
}


    // decode means verify token using Secret Key and getClaims
    private Claims getClaims(String token){
    return Jwts.parser().verifyWith((SecretKey) getKey())
             .build().parseSignedClaims(token)
             .getPayload();
    }

   // return token getExpiration time
    public Date extractExpiration(String token){
        return getClaims(token).getExpiration();
    }


}













