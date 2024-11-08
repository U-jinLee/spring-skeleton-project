package com.example.skeleton.domain.authentication.service;

import com.example.skeleton.domain.authentication.dto.SignInDto;
import com.example.skeleton.domain.authentication.dto.SignUpDto;

public interface AuthenticationService {
    SignUpDto.Response signUp(SignUpDto.Request request);
    SignInDto.Response signIn(SignInDto.Request request);
}