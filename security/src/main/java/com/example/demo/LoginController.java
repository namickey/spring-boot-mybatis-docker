package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String top() {
        return "top";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/item")
    public String item() {
        return "item";
    }
}
