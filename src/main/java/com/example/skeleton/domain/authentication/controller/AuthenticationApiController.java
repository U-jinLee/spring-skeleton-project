package com.example.skeleton.domain.authentication.controller;

import com.example.skeleton.domain.authentication.dto.SignInDto;
import com.example.skeleton.domain.authentication.dto.SignUpDto;
import com.example.skeleton.domain.authentication.dto.ValidateRequestDto;
import com.example.skeleton.domain.authentication.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/authentication")
@RestController
public class AuthenticationApiController {

    private final AuthenticationService authenticationService;

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
        return ResponseEntity.ok(this.authenticationService.signIn(request));
    }

    /**
     * 가입한 멤버의 계정 활성화
     * @param email - 활성화할 이메일
     * @param request - Code 정보
     * @return HttpStatus.OK
     */
    @PostMapping("/{email}/validate")
    public ResponseEntity<Object> validate(@PathVariable("email") String email, @RequestBody ValidateRequestDto request) {
        authenticationService.validate(email, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
