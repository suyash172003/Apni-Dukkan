package com.example.Dukkan.Service;

import java.util.List;

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
	
	@GetMapping("/home")
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
		if(cart!=null)
		cartOrder.save(cart);
		return "redirect:/orders";
	}
	
	@GetMapping("/cart")
	public String cart(Model model) {
		List<Cart> temp=cartOrder.findAll();
		model.addAttribute("cart", temp);
		int totalSum=0;
		for(Cart value:temp) {
			totalSum+=value.getPrice();
		}
		model.addAttribute("totalSum",totalSum);
		return "Cart";
	}
}
