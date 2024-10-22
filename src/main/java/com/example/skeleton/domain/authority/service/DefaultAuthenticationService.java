package com.example.skeleton.domain.authority.service;

import com.example.skeleton.domain.authority.dto.SignUpDto;
import com.example.skeleton.domain.authority.entity.Member;
import com.example.skeleton.domain.authority.entity.Role;
import com.example.skeleton.domain.authority.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DefaultAuthenticationService implements AuthenticationService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public DefaultAuthenticationService(MemberRepository memberRepository,
                                        PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SignUpDto.Response signUp(SignUpDto.Request request) {
        Member member = Member.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        return SignUpDto.Response.newInstance(this.memberRepository.save(member));
    }

}
