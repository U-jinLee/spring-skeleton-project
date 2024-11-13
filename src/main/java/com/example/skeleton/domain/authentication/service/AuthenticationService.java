package com.example.skeleton.domain.authentication.service;

import com.example.skeleton.domain.authentication.dto.SignInDto;
import com.example.skeleton.domain.authentication.dto.SignUpDto;
import com.example.skeleton.domain.authentication.dto.ValidateRequestDto;

public interface AuthenticationService {
    SignUpDto.Response signUp(SignUpDto.Request request);
    SignInDto.Response signIn(SignInDto.Request request);
    void validate(String email, ValidateRequestDto request);
}