package com.example.Dukkan.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Dukkan.Model.Login;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@GetMapping
	public String home(@ModelAttribute("login") Login login,HttpSession session,Model model) {
		if(session.getAttribute("email")==null)
			model.addAttribute("login", null);
		return "Home";
	}

}
