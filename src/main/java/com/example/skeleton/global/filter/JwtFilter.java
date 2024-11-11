package com.example.skeleton.global.filter;

import com.example.skeleton.global.service.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private static final String BEARER = "Bearer ";
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String jwt = parseJwt(request);
        if (StringUtils.hasText(jwt) && jwtProvider.validate(jwt))
            SecurityContextHolder.getContext().setAuthentication(jwtProvider.getAuthentication(jwt));

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(authorization) && authorization.startsWith(BEARER))
            stringBuilder.append(authorization.substring(7));

        return stringBuilder.toString();
    }

}
