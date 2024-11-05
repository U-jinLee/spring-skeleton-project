package com.example.skeleton.domain.authority.exception;

import com.example.skeleton.global.error.exception.BusinessException;
import com.example.skeleton.global.error.exception.ErrorCode;

public class PasswordNotMatchException extends BusinessException {

    public PasswordNotMatchException(String message) {
        super(message, ErrorCode.PASSWORD_NOT_MATCH);
    }

}
