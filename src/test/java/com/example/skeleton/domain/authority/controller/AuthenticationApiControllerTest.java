package com.example.skeleton.domain.authority.controller;

import com.example.skeleton.IntegrationTest;
import com.example.skeleton.domain.authority.dto.SignInDto;
import com.example.skeleton.domain.authority.dto.SignUpDto;
import com.example.skeleton.domain.authority.entity.Member;
import com.example.skeleton.domain.authority.entity.Role;
import com.example.skeleton.domain.authority.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthenticationApiControllerTest extends IntegrationTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원 가입 성공")
    void signUp() throws Exception {
        //given
        String email = "leeujin1029@naver.com";
        String password = "1q2w3e4r!";
        SignUpDto.Request request =
                new SignUpDto.Request(email, password);
        //when
        mvc.perform(post("/api/authentication/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                //then
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("email").value(email))
                .andExpect(jsonPath("enabled").value(false));
    }

    @Test
    @DisplayName("로그인 성공")
    void signIn() throws Exception {
        String email = "leeujin1029@naver.com";
        String password = "1q2w3e4r!";

        Member member = Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(Role.ROLE_USER)
                .enabled(true)
                .build();

        memberRepository.save(member);

        SignInDto.Request request = new SignInDto.Request(email, password);

        mvc.perform(post("/api/authentication/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(jsonPath("accessToken").isNotEmpty())
                .andExpect(jsonPath("refreshToken").isNotEmpty());
    }

}