package com.example.Dukkan.Service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Orders {
	
	@GetMapping("/dukkan")
	public String homePage() {
		return "home";
	}
	
	@GetMapping("/AttaDaal")
	public String orderAttaDaal() {
		return "AttaDaal";
	}
}
