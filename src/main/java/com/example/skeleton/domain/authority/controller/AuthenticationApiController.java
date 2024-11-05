package com.example.skeleton.domain.authority.controller;

import com.example.skeleton.domain.authority.dto.SignInDto;
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

    /**
     * 일반 회원 가입 서비스
     * @param request 회원가입에 필요한 Request
     * @return 회원가입된 값
     */
    @PostMapping("/sign-up")
    public ResponseEntity<SignUpDto.Response> signUp(@RequestBody @Valid SignUpDto.Request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.authenticationService.signUp(request));
    }

    /**
     * 일반 로그인 서비스
     * @param request 로그인에 필요한 Request
     * @return JWT 토큰 값
     */
    @PostMapping("/sign-in")
    public ResponseEntity<SignInDto.Response> signIn(@RequestBody @Valid SignInDto.Request request) {
        return ResponseEntity.ok(authenticationService.signIn(request));
    }

}
