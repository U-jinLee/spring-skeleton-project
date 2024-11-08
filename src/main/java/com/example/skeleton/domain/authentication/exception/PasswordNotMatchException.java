package com.example.skeleton.domain.authentication.exception;

import com.example.skeleton.global.error.exception.BusinessException;
import com.example.skeleton.global.error.exception.ErrorCode;

public class PasswordNotMatchException extends BusinessException {

    public PasswordNotMatchException() {
        super(ErrorCode.PASSWORD_NOT_MATCH);
    }

}
