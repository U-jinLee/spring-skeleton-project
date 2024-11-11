package com.example.skeleton.global.config;


import com.example.skeleton.domain.authentication.entity.Role;
import com.example.skeleton.domain.authentication.service.CustomOAuth2Service;
import com.example.skeleton.global.filter.JwtFilter;
import com.example.skeleton.global.service.JwtProvider;
import com.example.skeleton.global.service.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

    private final JwtProvider jwtProvider;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final CustomOAuth2Service customOAuth2Service;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webConfigure() {
        return web -> web.ignoring()
                .requestMatchers(toH2Console());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable();

        http
                .authorizeHttpRequests(req ->
                        req
                                .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole(Role.ROLE_ADMIN.getValue())
                                .requestMatchers(new AntPathRequestMatcher("/api/authentication/**"),
                                        new AntPathRequestMatcher("/login")).permitAll()
                                .anyRequest().authenticated())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);
        // H2 Console
        http
                .headers()
                .frameOptions()
                .sameOrigin();
        // OAuth2
        http
                .oauth2Login()
                .successHandler(oAuth2SuccessHandler)
                .userInfoEndpoint()
                .userService(customOAuth2Service);


        return http.build();
    }
}
