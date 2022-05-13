package com.example.demo.regist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistController {
    @GetMapping("/regist")
    public String index() {
        return "regist";
    }
}
