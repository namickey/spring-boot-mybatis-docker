package com.example.demo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty("hello.base64")
public class Base64Impl implements Base64 {
    @Override
    public String toBase64(String str) {
        return java.util.Base64.getEncoder().encodeToString(str.getBytes());
    }
}
