package com.example.demo;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class HelloController {

    @PostMapping("/hello")
    @ResponseBody
    public Object hello(@Valid @RequestBody User user, BindingResult result) {
        if(result.hasErrors()){
            return result.getFieldError();
        }
        System.out.println(user);
        return new Item("okashi", 100);
    }
}
