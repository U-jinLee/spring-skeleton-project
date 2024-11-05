package com.example.skeleton.domain.authority.service;

import com.example.skeleton.domain.authority.dto.SignInDto;
import com.example.skeleton.domain.authority.dto.SignUpDto;
import com.example.skeleton.domain.authority.entity.Member;
import com.example.skeleton.domain.authority.entity.Role;
import com.example.skeleton.domain.authority.exception.MemberNotFoundException;
import com.example.skeleton.domain.authority.exception.NotEnableMemberException;
import com.example.skeleton.domain.authority.exception.PasswordNotMatchException;
import com.example.skeleton.domain.authority.repository.MemberRepository;
import com.example.skeleton.global.service.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DefaultAuthenticationService implements AuthenticationService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public DefaultAuthenticationService(MemberRepository memberRepository,
                                        PasswordEncoder passwordEncoder,
                                        JwtProvider jwtProvider) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public SignUpDto.Response signUp(SignUpDto.Request request) {
        Member member = Member.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .enabled(false)
                .build();

        return SignUpDto.Response.newInstance(this.memberRepository.save(member));
    }

    @Override
    public SignInDto.Response signIn(SignInDto.Request request) {

        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new MemberNotFoundException(request.getEmail()));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword()))
            throw new PasswordNotMatchException("패스워드 불일치");

        if (Boolean.FALSE.equals(member.isEnabled()))
            throw new NotEnableMemberException("활성화 되지 않은 멤버입니다.");

        return SignInDto.Response
                .newInstance(jwtProvider.createAccessToken(member), jwtProvider.createRefreshToken(member));
    }

}
