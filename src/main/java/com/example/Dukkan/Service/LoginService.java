package com.example.Dukkan.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Dukkan.Model.Login;
import com.example.Dukkan.Repository.LoginRepository;
import com.example.Dukkan.Repository.RegisterRepository;

@Controller
public class LoginService {
	
	@Autowired
	LoginRepository logging;
	@Autowired
	RegisterRepository registering;
	
	@GetMapping("/login")
	public String loginUser(Model model) {
		model.addAttribute("login",new Login());
		return "login";
	}
	
	@PostMapping("/login")
	public String loginUser(Login login,Model model) {
		if(registering.existsByEmail(login.getEmail())) {
			if(login.getPassword().equals(registering.findByEmail(login.getEmail()).getPassword())) {
				logging.save(login);
				return "home";
			}
		}
		model.addAttribute("errorMessage", "Invalid Credentials");
		return "login";
	}
	
	@GetMapping("/logout")
	public String logoutUser(Model model) {
		return "redirect:/home";
	}
	
}
