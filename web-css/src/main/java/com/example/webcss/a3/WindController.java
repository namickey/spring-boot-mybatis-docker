package com.example.webcss.a3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("wind")
public class WindController {

    @GetMapping("display")
    public String display(){
        return "wind/entry";
    }

    @GetMapping("confirm")
    public String confirm(){
        return "wind/confirm";
    }

    @GetMapping("entry")
    public String entry(){
        return "wind/complete";
    }
}
