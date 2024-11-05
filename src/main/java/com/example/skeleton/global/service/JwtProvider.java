package com.example.skeleton.global.service;

import com.example.skeleton.domain.authority.entity.Member;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
    private static final String AUTHORITIES_KEY = "auth";
    private final String secretKey;
    private final long accessTokenValidityInMillisecond;
    private Key key;

    public JwtProvider(@Value("${jwt.secret}") String secretKey,
                       @Value("${jwt.live.access}") long accessLiveMillisecond) {
        this.secretKey = secretKey;
        this.accessTokenValidityInMillisecond = accessLiveMillisecond;
    }

    @PostConstruct
    private void initialize() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createAccessToken(Member member) {
        return createToken(member, this.accessTokenValidityInMillisecond);
    }

    public String createRefreshToken(Member member) {
        return createToken(member, this.accessTokenValidityInMillisecond);
    }

    private String createToken(Member member, long tokenValidityMillisecond) {
        return Jwts.builder()
                .setSubject(member.getEmail())
                .claim(AUTHORITIES_KEY, member.getRole())
                .signWith(this.key, SignatureAlgorithm.HS512)
                .setExpiration(new Date(new Date().getTime() + tokenValidityMillisecond))
                .compact();
    }


}