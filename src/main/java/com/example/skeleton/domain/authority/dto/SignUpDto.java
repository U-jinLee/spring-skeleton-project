package com.example.skeleton.domain.authority.dto;

import com.example.skeleton.domain.authority.entity.Member;
import lombok.Getter;

public class SignUpDto {
    private SignUpDto() {}

    @Getter
    public static class Request {
        private String email;
        private String password;

        public Request(String email, String password) {
            this.email = email;
            this.password = password;
        }

    }

    public static class Response {
        private long id;
        private String email;

        public Response(long id, String email) {
            this.id = id;
            this.email = email;
        }

        public static SignUpDto.Response newInstance(Member member) {
            return new SignUpDto.Response(member.getId(), member.getEmail());
        }

        public long getId() {
            return id;
        }

        public String getEmail() {
            return this.email;
        }

    }

}
