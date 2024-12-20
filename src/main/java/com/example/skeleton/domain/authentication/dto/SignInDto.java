package com.example.skeleton.domain.authentication.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;

public class SignInDto {
    private SignInDto() {}

    @Getter
    public static class Request {
        @Email
        private String email;
        private String password;

        public Request(String email, String password) {
            this.email = email;
            this.password = password;
        }

    }

    @Getter
    public static class Response {
        private String accessToken;
        private String refreshToken;

        public Response(String accessToken, String refreshToken) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }

        public static SignInDto.Response newInstance(String accessToken, String refreshToken) {
            return new SignInDto.Response(accessToken, refreshToken);
        }

    }

}
