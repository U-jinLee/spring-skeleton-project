package com.example.skeleton.global.util;

import java.util.concurrent.ThreadLocalRandom;

public class CodeGeneratorUtil {
    private static final int LEFT_LIMIT = 48; // '0'
    private static final int RIGHT_LIMIT = 122; // 'z'
    private static final int TARGET_STRING_LENGTH = 6;

    private CodeGeneratorUtil() {}

    public static String generate() {
        return ThreadLocalRandom.current()
                .ints(LEFT_LIMIT, RIGHT_LIMIT + 1)
                .filter(i -> (i <=57 || i >=65) && (i <= 90 || i>= 97))
                .limit(TARGET_STRING_LENGTH)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
