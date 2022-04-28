package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String top() {
        return "top";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/item")
    public String item(Model model) {
        model.addAttribute("itemForm", new ItemForm());
        return "item";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute ItemForm itemForm, BindingResult result){
        if (result.hasErrors()) {
            return "item";
        }
        System.out.println(itemForm.getId());
        System.out.println(itemForm.getName());
        return "redirect:/";
    }
}
