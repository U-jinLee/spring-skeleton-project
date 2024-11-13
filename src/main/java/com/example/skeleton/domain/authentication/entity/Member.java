package com.example.skeleton.domain.authentication.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Table(name = "member")
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Registration registration;

    @Column(name = "enabled")
    private Boolean enabled;

    @Builder
    public Member(String email, String password, Role role, Registration registration, boolean enabled) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.registration = registration;
        this.enabled = enabled;
    }

    public String getSubject() {
        return new StringBuilder()
                .append(this.email)
                .append("_")
                .append(this.registration)
                .toString();
    }

    public void enabled() {
        this.enabled = true;
    }

    public static Member newUser(String email, String password) {
        return Member.builder()
                .email(email)
                .password(password)
                .role(Role.ROLE_USER)
                .registration(Registration.DEFAULT)
                .enabled(false)
                .build();
    }

}