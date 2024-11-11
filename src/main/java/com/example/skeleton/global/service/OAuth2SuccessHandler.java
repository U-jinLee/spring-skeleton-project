package com.example.skeleton.global.service;

import com.example.skeleton.domain.authentication.entity.Member;
import com.example.skeleton.domain.authentication.entity.Registration;
import com.example.skeleton.domain.authentication.entity.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final JwtProvider jwtProvider;
    private static final String URI = "/login";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Member entity = Member.builder()
                .email(authentication.getName())
                .registration(Registration.GOOGLE)
                .role(Role.ROLE_USER)
                .build();

        String accessToken = jwtProvider.createAccessToken(entity);

        String redirectUrl = UriComponentsBuilder.fromUriString(URI)
                .queryParam(JwtProvider.ACCESS_TOKEN, accessToken).build().toString();

        response.sendRedirect(redirectUrl);
    }
}
