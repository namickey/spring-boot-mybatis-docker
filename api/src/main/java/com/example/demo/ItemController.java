package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    @GetMapping("/item")
    @ResponseBody
    public Object item(){
        return new Item("kocha", 100);
    }

    @GetMapping("/")
    @ResponseBody
    public Object helth(){
        return "success";
    }
}
