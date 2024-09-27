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
public class Categories {
	
	@Autowired
	CartRepository cartOrder;
	
	@GetMapping("/home")
	public String homePage() {
		return "home";
	}
	
	
	@GetMapping("/category1")
	public String category1(Model model) {
		model.addAttribute("cart",new Cart());
		return "category1";
	}
	
	@GetMapping("/category2")
	public String category2(Model model) {
		model.addAttribute("cart",new Cart());
		return "category2";
	}
	@GetMapping("/category3")
	public String category3(Model model) {
		model.addAttribute("cart",new Cart());
		return "category3";
	}
	@GetMapping("/category4")
	public String category4(Model model) {
		model.addAttribute("cart",new Cart());
		return "category4";
	}
	
	
	@PostMapping("/category1")
	public String category1(@ModelAttribute("cart")Cart cart) {
		if(cart!=null)
		cartOrder.save(cart);
		return "redirect:/category1";
	}
	
	@PostMapping("/category2")
	public String category2(@ModelAttribute("cart")Cart cart) {
		if(cart!=null)
		cartOrder.save(cart);
		return "redirect:/category2";
	}
	
	@PostMapping("/category3")
	public String category3(@ModelAttribute("cart")Cart cart) {
		if(cart!=null)
		cartOrder.save(cart);
		return "redirect:/category3";
	}
	

	@PostMapping("/category4")
	public String category4(@ModelAttribute("cart")Cart cart) {
		if(cart!=null)
		cartOrder.save(cart);
		return "redirect:/category4";
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
