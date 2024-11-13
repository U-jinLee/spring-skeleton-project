package com.example.skeleton.global.entity;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RedisHash(value = "validateCode", timeToLive = 60 * 3)
public class ValidateCode {
    @Id
    private String id;

    @Indexed
    private String code;

    public static ValidateCode create(String email, String code) {
        return new ValidateCode(email, code);
    }

}
