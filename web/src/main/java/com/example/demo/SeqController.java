package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeqController {

    @Autowired
    private SeqMapper mapper;

    @GetMapping("/f1")
    public int getFishNext(){
        return mapper.nextval("fish");
    }

    @GetMapping("/f2")
    public int getFishCurr(){
        return mapper.currval("fish");
    }

    @GetMapping("/f3")
    public int getFireNext(){
        return mapper.nextval("fire");
    }

    @GetMapping("/f4")
    public int getFireCurr(){
        return mapper.currval("fire");
    }
}
