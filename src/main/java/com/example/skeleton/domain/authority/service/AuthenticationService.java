package com.example.skeleton.domain.authority.service;

import com.example.skeleton.domain.authority.dto.SignUpDto;

public interface AuthenticationService {
    SignUpDto.Response signUp(SignUpDto.Request request);
}