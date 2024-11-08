package com.example.skeleton.domain.authority.dto;

import com.example.skeleton.domain.authority.entity.Member;
import jakarta.validation.constraints.Email;
import lombok.Getter;

public class SignUpDto {
    private SignUpDto() {}

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
        private long id;
        private String email;
        private boolean enabled;

        public Response(long id, String email, boolean enabled) {
            this.id = id;
            this.email = email;
            this.enabled = enabled;
        }

        public static SignUpDto.Response newInstance(Member member) {
            return new SignUpDto.Response(member.getId(), member.getEmail(), member.getEnabled());
        }

    }

}
