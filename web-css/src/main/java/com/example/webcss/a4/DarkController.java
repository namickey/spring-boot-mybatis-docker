package com.example.webcss.a4;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dark")
public class DarkController {

    @GetMapping("display")
    public String display(){
        return "dark/entry";
    }

    @GetMapping("confirm")
    public String confirm(){
        return "dark/confirm";
    }

    @GetMapping("entry")
    public String entry(){
        return "dark/complete";
    }
}
