package com.example.skeleton.domain.authentication.exception;

import com.example.skeleton.global.error.exception.EntityNotFoundException;

public class MemberNotFoundException extends EntityNotFoundException {

    public MemberNotFoundException(String target) {
        super(target + " is not found");
    }
}
