package com.example.Dukkan.Controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Dukkan.Model.Login;
import com.example.Dukkan.Model.Otp;
import com.example.Dukkan.Model.Register;
import com.example.Dukkan.Repository.RegisterRepository;
import com.example.Dukkan.Service.EmailService;

@Controller
@SessionAttributes("register")
public class RegisterService {
	
	@Autowired
	private RegisterRepository registering;
	
	@Autowired
	EmailService emailGenerate;
	
	Random random=new Random(10001);
	
	@GetMapping("/register")
	public String registerUser(Model model) {
		model.addAttribute("register", new Register());
		return "Register";
	}
	
	@PostMapping("/register")
	public String registerUser(RedirectAttributes redirect,Register register,Model model) {
		if(registering.existsByEmail(register.getEmail())) {
			redirect.addFlashAttribute("errorMessage", "Email already registered! Try logging in.");
			return "redirect:/register";
		}
		else {
			int val=random.nextInt(9999);
			Otp otp=new Otp();
			otp.setGotp(String.valueOf(val));
			emailGenerate.sendEmailTo(register.getEmail(),"From APNI DUKKAN", "Your otp:"+String.valueOf(val));
			model.addAttribute("otp",otp);
			return "RegisterOtp";
		}
	}
	
	@PostMapping("/register/verify-otp")
	public String verification(@ModelAttribute("otp") Otp otp,RedirectAttributes redirect,@ModelAttribute("formEmail") Login formEmail,@ModelAttribute Register register,Model model){
		if(otp.getEotp().compareTo(otp.getGotp())==0) {
			redirect.addFlashAttribute("message","OTP Verified Successfully");
			registering.save(register);
		}
		else {
			model.addAttribute("message","Otp Verification UnSuccessfull");
		}
		return "redirect:/login";
	}
	
}