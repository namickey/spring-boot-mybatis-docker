package com.example.webcss;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopController {

    @GetMapping("/")
    public String index(){
        return "top";
    }
}
