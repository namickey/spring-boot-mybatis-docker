package com.example.webcss.a1;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("fire")
public class FireController {

    @GetMapping("display")
    public String display(){
        return "fire/entry";
    }

    @GetMapping("confirm")
    public String confirm(){
        return "fire/confirm";
    }

    @GetMapping("entry")
    public String entry(){
        return "fire/complete";
    }
}
