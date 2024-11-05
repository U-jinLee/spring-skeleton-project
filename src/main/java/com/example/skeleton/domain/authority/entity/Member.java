package com.example.skeleton.domain.authority.entity;

import jakarta.persistence.*;
import lombok.Builder;

@Table(name = "member")
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

    private Boolean enabled;

    @Builder
    public Member(String email, String password, Role role, boolean enabled) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }

    public long getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {return this.password;}


    public Role getRole() {
        return this.role;
    }

    public Boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }

}