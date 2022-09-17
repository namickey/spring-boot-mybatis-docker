package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private HelloProperties helloProperties;

    @Autowired
    private Item item;

    @Autowired
    private Base64 base64;

    @GetMapping
    @ResponseBody
    public Object hello(){
        System.out.println("********************");
        System.out.println(helloProperties.getWord());
        System.out.println("********************");
        System.out.println(item.getName());
        System.out.println("********************");
        System.out.println(base64.toBase64("aiueokakikukeko"));
        return "hello world";
    }
}
