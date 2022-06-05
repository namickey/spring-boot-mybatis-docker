package com.example.webcss;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EntryController {

    @GetMapping("index")
    public String index(){

        return "index";
    }

    @GetMapping("confirm")
    public String confirm(){

        return "confirm";
    }

    @GetMapping("entry")
    public String entry(){

        return "complete";
    }
}
