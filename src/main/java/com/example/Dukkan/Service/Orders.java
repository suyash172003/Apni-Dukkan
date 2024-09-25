package com.example.Dukkan.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Dukkan.Model.Cart;
import com.example.Dukkan.Repository.CartRepository;

@Controller
public class Orders {
	
	@Autowired
	CartRepository cartOrder;
	
	@GetMapping("/dukkan")
	public String homePage() {
		return "home";
	}
	
	@GetMapping("/orders")
	public String orderAttaDaal(Model model) {
		model.addAttribute("cart",new Cart());
		return "AttaDaal";
	}
	
	@PostMapping("/orders")
	public String orderAttaDaal(@ModelAttribute("cart")Cart cart) {
		System.out.print(cart);
		cartOrder.save(cart);
		return "redirect:/orders";
	}
}
