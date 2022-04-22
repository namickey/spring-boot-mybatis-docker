package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

	@GetMapping
	public String top() {
		
		return "top";
	}
	
	@GetMapping("/jusho")
	@ResponseBody
	public Object getJusho() {
		return List.of("東京", "神奈川");
	}
}
