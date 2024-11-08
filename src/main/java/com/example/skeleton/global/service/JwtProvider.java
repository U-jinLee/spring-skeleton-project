package com.example.skeleton.global.service;

import com.example.skeleton.domain.authority.entity.Member;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class JwtProvider {
    private static final String AUTHORITIES_KEY = "auth";
    private final String secretKey;
    private final long accessTokenValidityInMillisecond;
    private final long refreshTokenValidityInMillisecond;
    private Key key;

    public JwtProvider(@Value("${jwt.secret}") String secretKey,
                       @Value("${jwt.live.access}") long accessLiveMillisecond,
                       @Value("${jwt.live.refresh}") long refreshLiveMillisecond) {
        this.secretKey = secretKey;
        this.accessTokenValidityInMillisecond = accessLiveMillisecond;
        this.refreshTokenValidityInMillisecond = refreshLiveMillisecond;
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
        return createToken(member, this.refreshTokenValidityInMillisecond);
    }

    private String createToken(Member member, long tokenValidityMillisecond) {
        return Jwts.builder()
                .setSubject(member.getEmail())
                .claim(AUTHORITIES_KEY, member.getRole().name())
                .setExpiration(new Date(new Date().getTime() + tokenValidityMillisecond))
                .signWith(this.key, SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean validate(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Wrong JWT");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT");
        } catch (IllegalArgumentException e) {
            log.info("Illegal argument");
        }

        return false;
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token).getBody();
        return new UsernamePasswordAuthenticationToken(claims.getSubject(), new String(),
                List.of(new SimpleGrantedAuthority(claims.get(AUTHORITIES_KEY).toString())));
    }

}