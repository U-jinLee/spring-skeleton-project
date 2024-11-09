package com.example.skeleton.domain.authentication.service;

import com.example.skeleton.domain.authentication.dto.SignInDto;
import com.example.skeleton.domain.authentication.dto.SignUpDto;
import com.example.skeleton.domain.authentication.entity.Member;
import com.example.skeleton.domain.authentication.entity.Role;
import com.example.skeleton.domain.authentication.exception.MemberNotFoundException;
import com.example.skeleton.domain.authentication.exception.LoginFailureException;
import com.example.skeleton.domain.authentication.repository.MemberRepository;
import com.example.skeleton.global.service.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultAuthenticationService implements AuthenticationService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public SignUpDto.Response signUp(SignUpDto.Request request) {
        Member member = Member.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .enabled(false)
                .build();

        return SignUpDto.Response.newInstance(this.memberRepository.save(member));
    }

    @Override
    public SignInDto.Response signIn(SignInDto.Request request) {

        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new MemberNotFoundException(request.getEmail()));

        if (passwordEncoder.matches(request.getPassword(), member.getPassword()) &&
                Boolean.TRUE.equals(member.getEnabled()))

            return SignInDto.Response
                    .newInstance(jwtProvider.createAccessToken(member), jwtProvider.createRefreshToken(member));

        throw new LoginFailureException();
    }

}
