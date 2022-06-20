package com.example.webcss.a2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("water")
public class WaterController {

    @GetMapping("display")
    public String display(){
        return "water/entry";
    }

    @GetMapping("confirm")
    public String confirm(){
        return "water/confirm";
    }

    @GetMapping("entry")
    public String entry(){
        return "water/complete";
    }
}
