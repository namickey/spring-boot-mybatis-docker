package com.example.demo;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@RestController
public class HelloController {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @PostMapping("/full")
    @ResponseBody
    public Object full(@Valid @RequestBody User user, BindingResult result, RestTemplate restTemplate) {
        if(result.hasErrors()){
            return result.getFieldError();
        }
        System.out.println(user);
        String body = restTemplate.postForObject("http://localhost:5000/hello", user, String.class);
        System.out.println(body);
        return user;
    }

    @PostMapping("/rest")
    @ResponseBody
    public Object rest(@Valid @RequestBody User user, BindingResult result, RestTemplate restTemplate) {
        if(result.hasErrors()){
            return result.getFieldError();
        }
        System.out.println(user);
        String body = restTemplate.postForObject("http://localhost:5000/hello", user, String.class);
        System.out.println(body);
        return user;
    }
}
