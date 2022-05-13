package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    ItemService itemService;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {

        String word = itemService.getItem(name);
        return String.format("Hello %s!", name + " " + word);
    }

    @GetMapping("/bye")
    public String bye(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Bye %s!", name);
    }

    @GetMapping("/jusho")
    @ResponseBody
    public Object getJusho() {
        Item item = new Item();
        item.setId("yaya");
        item.setName("gaga");
        return item;
    }

    @GetMapping("/outapi")
    public String outapi(RestTemplate restTemplate){
        Item item = restTemplate.getForObject("http://localhost:8080/jusho", Item.class);
        System.out.println(item);
        return item.getName();
    }
}
