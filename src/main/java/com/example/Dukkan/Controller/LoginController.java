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
import com.example.Dukkan.Model.Password;
import com.example.Dukkan.Model.Register;
import com.example.Dukkan.Repository.LoginRepository;
import com.example.Dukkan.Repository.RegisterRepository;
import com.example.Dukkan.Service.EmailService;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("formEmail")
public class LoginController {
	
	@Autowired
	LoginRepository logging;
	
	@Autowired
	RegisterRepository registering;
	
	@Autowired
	EmailService emailGenerate;

	Random generate=new Random(1000);
	
	@GetMapping("/login")
	public String loginUser(Model model) {
		model.addAttribute("login",new Login());
		return "Login";
	}
	
	@PostMapping("/login")
	public String loginUser(@ModelAttribute("login") Login login,RedirectAttributes redirectAttribute,HttpSession session) {
		if(registering.existsByEmail(login.getEmail())) {
			if(login.getPassword().equals(registering.findByEmail(login.getEmail()).getPassword())) {
				session.setAttribute("email", login.getEmail());
				Register register=registering.findByEmail((String)session.getAttribute("email"));
				session.setAttribute("userId",register.getId().longValue());
				logging.save(login);
				return "Home";
			}
		}
		redirectAttribute.addFlashAttribute("errorMessage", "Invalid Credentials");
		return "redirect:/login";
	}
	
	@GetMapping("/forget-password")
	public String forgetPassword(Model model) {
		model.addAttribute("formEmail", new Login());
		return "Forget";
	}
	
	@PostMapping("/forget-password")
	public String otp(Model model,@ModelAttribute("formEmail") Login formEmail, RedirectAttributes redirectAttributes) {
		
		if(registering.findByEmail(formEmail.getEmail())==null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Not registered with this email Id!!");
			return "redirect:/forget-password";
		}else {
			int val=generate.nextInt(9999);
			Otp otp=new Otp();
			otp.setGotp(String.valueOf(val));
			emailGenerate.sendEmailTo(formEmail.getEmail(),"From APNI DUKKAN", "Your otp:"+String.valueOf(val));
			model.addAttribute("otp",otp);
			return "OtpPage";
		}
	}
	
	@PostMapping("/verify-otp")
	public String verification(@ModelAttribute("otp") Otp otp,RedirectAttributes redirect,@ModelAttribute("formEmail") Login formEmail,Model model){
		System.out.println(formEmail.getEmail());
		if(otp.getEotp().compareTo(otp.getGotp())==0) {
			model.addAttribute("message","OTP Verified Successfully");
			model.addAttribute("password",new Password());
			return "SetPassword";
		}
		else {
			redirect.addFlashAttribute("message","Otp Verification UnSuccessfull");
			return "redirect:/forget-password";
		}
	}
	
	
	@PostMapping("/set-password")
	public String setPassword(@ModelAttribute("password") Password password,@ModelAttribute("formEmail") Login formEmail,RedirectAttributes redirect){
		Register register=registering.findByEmail(formEmail.getEmail());
		register.setPassword(password.getNewPassword());
		registering.save(register);
		redirect.addFlashAttribute("message", "Password Changed Successfully!!");
		return "redirect:/login";
	}
	
	
	@GetMapping("/logout")
	public String logoutUser(Model model,HttpSession session,Login login) {
		session.invalidate();
		model.addAttribute("login", null);
		return "Home";
	}
	
}
