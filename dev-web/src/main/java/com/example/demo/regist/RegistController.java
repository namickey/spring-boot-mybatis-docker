package com.example.demo.regist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/regist")
public class RegistController {
    @GetMapping("/disp")
    public String index() {
        return "regist";
    }

    @GetMapping("/confirm")
    public String confirm() {
        return "confirm";
    }

    @GetMapping("/regist")
    public String regist() {
        return "redirect:/regist/comp";
    }

    @GetMapping("/comp")
    public String comp() {
        return "comp";
    }
}
