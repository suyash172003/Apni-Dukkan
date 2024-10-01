package com.example.Dukkan.Service;

import java.util.Random;

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
	Random generate=new Random(1000);
	@GetMapping("/login")
	public String loginUser(Model model) {
		model.addAttribute("login",new Login());
		return "login";
	}
	
	@GetMapping("/forget-password")
	public String forgetPassword() {
		return "Forget";
	}
	
	@PostMapping("/forget-password")
	public String otp(Model model) {
		int val=generate.nextInt(9999);
		System.out.println(val);
		return "otpPage";
	}
	
	@PostMapping("/verify-otp")
	public String verification(){
		
		return "redirect:/otpPage";
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
