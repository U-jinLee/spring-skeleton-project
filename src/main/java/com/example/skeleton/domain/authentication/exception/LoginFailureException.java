package com.example.skeleton.domain.authentication.exception;

import com.example.skeleton.global.error.exception.BusinessException;
import com.example.skeleton.global.error.exception.ErrorCode;

public class LoginFailureException extends BusinessException {
    public LoginFailureException() {
        super(ErrorCode.LOGIN_FAILURE);
    }
}
