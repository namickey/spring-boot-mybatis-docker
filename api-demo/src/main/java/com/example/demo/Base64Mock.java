package com.example.demo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "hello.base64", havingValue = "false", matchIfMissing = true)
public class Base64Mock implements Base64 {
    @Override
    public String toBase64(String str) {
        return str;
    }
}
