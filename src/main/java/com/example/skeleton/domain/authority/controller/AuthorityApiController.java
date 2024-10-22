package com.example.skeleton.domain.authority.controller;

import com.example.skeleton.domain.authority.dto.SignUpDto;
import com.example.skeleton.domain.authority.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/authentication")
@RestController
public class AuthorityApiController {

    private final AuthenticationService authenticationService;

    public AuthorityApiController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpDto.Response> signUp(@RequestBody SignUpDto.Request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.authenticationService.signUp(request));
    }

}
