package com.example.skeleton.domain.authentication.exception;

import com.example.skeleton.global.error.exception.BusinessException;
import com.example.skeleton.global.error.exception.ErrorCode;

public class NotEnableMemberException extends BusinessException {

    public NotEnableMemberException(String message) {
        super(message, ErrorCode.NOT_ENABLE_MEMBER);
    }

}
