package com.example.helloworld.utils;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

//generate token and check it is valid or not
@Component
public class jwtutil {
    private final String SECRET=" sandhiya isss a gooodiiiiiiieeeeee girlllll !!!!!!!!! download aand share to like subsss";
    //if the hacker can see this page then he can easily get it so we have to encrypt and store it so we the hmac and sha
    //normally we will store it in environmental variable and use it
    //we create token with this secret --> without secret the token cannot be created
    private final long expiration=1000*60*60;
    //the token will be valid till that expiration time
    //secret should be in key not as String or else json-->(jwt method(jwt-->jason web token)) doesn't accept
    private final Key secret= Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    //hmac and sha are encryption algorithm and every char into bytes and convert it as array
    public String generateToken(String email){
        return Jwts.builder().setSubject(email)
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(secret, SignatureAlgorithm.HS256)
                //the above is used to seal the token
                 .compact();
    }
    public String extractUtil(String token){
        return Jwts.parserBuilder()
                //open the seal
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                //to check whether the token is correct or not
                .getBody()
                .getSubject();
    }
    public boolean validateToken(String token){
        try{
            extractUtil(token);
            return true;
        }catch(JwtException exception){
            return false;
        }
    }
}
