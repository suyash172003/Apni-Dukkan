package com.example.Dukkan.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Dukkan.Model.Register;
import com.example.Dukkan.Repository.RegisterRepository;

@Controller
public class RegisterService {
	
	@Autowired
	private RegisterRepository registering;
	
	@GetMapping("/register")
	public String registerUser(Model model) {
		model.addAttribute("register", new Register());
		return "register";
	}
	
	@PostMapping("/register")
	public String registerUser(Register register) {
		registering.save(register);
		return "redirect:/login";
	}

}
