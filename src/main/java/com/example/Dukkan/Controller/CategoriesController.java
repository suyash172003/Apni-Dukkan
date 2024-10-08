package com.example.Dukkan.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.Dukkan.Model.Cart;
import com.example.Dukkan.Model.Order;
import com.example.Dukkan.Model.Order1;
import com.example.Dukkan.Model.Register;
import com.example.Dukkan.Repository.CartRepository;
import com.example.Dukkan.Repository.OrderRepository;
import com.example.Dukkan.Repository.RegisterRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Controller
public class CategoriesController {
	
	@Autowired
	CartRepository cartOrder;
	
	@Autowired
	RegisterRepository registering;
	
	@Autowired
	OrderRepository ordering;
		
	@GetMapping({"/category1","/category2","/category3","/category4"})
	public String categoryGet(Model model,HttpServletRequest request,HttpSession session) {
		Cart cart=new Cart();
		Register register=registering.findByEmail((String)session.getAttribute("email"));
		if(register!=null) {
			session.setAttribute("userId",register.getId().longValue());
			cart.setUserId(register.getId());
		}
		model.addAttribute("cart",cart);
		String path=request.getRequestURI().substring(1);
		return path;
	}
	
	@PostMapping({"/category1","/category2","/category3","/category4"})
	public String categoryPost(@ModelAttribute("cart")Cart cart,HttpServletRequest request) {
		if(cart!=null)
		cartOrder.save(cart);
		String path=request.getRequestURI().substring(1);
		return "redirect:/"+path;
	}
	
	@GetMapping("/cart")
	public String cart(Model model,HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		List<Cart> temp=cartOrder.findByUserId(userId);
		model.addAttribute("temp", temp);
		Double totalSum=(double) 0;
		for(Cart value:temp) {
			totalSum+=value.getPrice();
		}
		model.addAttribute("totalSum",totalSum);
		Order order=new Order();
		order.setCurrency("CAD");
		order.setMethod("paypal");
		order.setDescription("Order Items");
		order.setSuccessUrl("http://localhost:8081/paymentSuccess");
		order.setCancelUrl("http://localhost:8081/paymentFailed");
		order.setIntent("sale");
		order.setTotal(totalSum);
		model.addAttribute("order",order);
		model.addAttribute("delete",new Cart());
		return "Cart";
	}
	
	@GetMapping("/orders")
	public String orders(Model model,HttpSession session) {
		Long userId=(Long) session.getAttribute("userId");
		
		Sort sort = Sort.by(Sort.Order.desc("date"));
	    List<Order1> list = ordering.findAllByUserId(userId, sort);
	    model.addAttribute("orders", list);
		return "Orders";
	}
	
	@Transactional
	@PostMapping("/delete")
	public String deleteElements(@ModelAttribute("delete")Cart delete) {
		Long orderId=delete.getOrderId();
		Long userId=delete.getUserId();
		cartOrder.deleteByUserIdAndOrderId(userId,orderId);
		return "redirect:/cart";
	}
}
