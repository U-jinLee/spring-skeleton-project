package com.example.skeleton.domain.authority.controller;

import com.example.skeleton.domain.authority.dto.SignUpDto;
import com.example.skeleton.domain.authority.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/authentication")
@RestController
public class AuthenticationApiController {

    private final AuthenticationService authenticationService;

    public AuthenticationApiController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<Object> signIn() {
        return ResponseEntity.ok(null);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpDto.Response> signUp(@RequestBody @Valid SignUpDto.Request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.authenticationService.signUp(request));
    }

}
