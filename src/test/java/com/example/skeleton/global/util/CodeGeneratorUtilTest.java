package com.example.skeleton.global.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodeGeneratorUtilTest {

    @Test
    @DisplayName("코드 생성기 테스트")
    void generate() {
        assertEquals(6, CodeGeneratorUtil.generate().length());
    }
}