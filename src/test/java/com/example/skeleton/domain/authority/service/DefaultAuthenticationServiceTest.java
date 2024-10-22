package com.example.skeleton.domain.authority.service;

import com.example.skeleton.IntegrationTest;
import com.example.skeleton.domain.authority.dto.SignUpDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DefaultAuthenticationServiceTest extends IntegrationTest {

    @Test
    @DisplayName("회원 가입 성공")
    void signUp() throws Exception {
        String email = "leeujin1029@naver.com";
        SignUpDto.Request request =
                new SignUpDto.Request(email, "1q2w3e4r!");

        mvc.perform(post("/api/authentication/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("email").value(email));
    }
}