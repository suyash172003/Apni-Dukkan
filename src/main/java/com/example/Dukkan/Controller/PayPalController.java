package com.example.Dukkan.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Dukkan.Model.Cart;
import com.example.Dukkan.Model.Order;
import com.example.Dukkan.Model.Order1;
import com.example.Dukkan.Repository.CartRepository;
import com.example.Dukkan.Repository.OrderRepository;
import com.example.Dukkan.Service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Controller
public class PayPalController {
	
	@Autowired
	PaypalService service;
	
	@Autowired
	CartRepository cartOrder;
	
	@Autowired 
	OrderRepository order_repo;
	
	@PostMapping("/payment")
	public String createPayment(Model model,@ModelAttribute("order") Order order,HttpSession session) throws PayPalRESTException {
		Payment payment=service.createPayment(order.getTotal(), order.getCurrency(),order.getMethod(), order.getIntent(), order.getDescription(),order.getCancelUrl(),order.getSuccessUrl());
Order1 order_list=new Order1();
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		
		Long userId=(Long) session.getAttribute("userId");
		
		List<Cart> temp=cartOrder.findByUserId(userId);
		
		List<String> item_list=new ArrayList<String>();
		for(Cart it:temp)
		item_list.add(it.getOrdername());
		
		order_list.setDate(currentDateTime);
		order_list.setUserId(userId);
		order_list.setItems(item_list);
		order_list.setStatus("successfull");
		order_list.setAmount(order.getTotal());
		
		session.setAttribute("order_list", order_list);
		
		for(Links link:payment.getLinks()) {
			if(link.getRel().equals("approval_url")) {
				return "redirect:"+link.getHref();
			}
		}
		return "redirect:/orders";
	}
	
	@GetMapping("/paymentSuccess")
	@Transactional
	public String paymentSuccess(@RequestParam String paymentId,@RequestParam String PayerID,HttpSession session) throws PayPalRESTException {
		Payment payment=service.executePayment(paymentId, PayerID);
		Order1  order_list=(Order1) session.getAttribute("order_list");
		if(payment.getState().equals("approved")) {
			order_list.setStatus("successfull");
			Long userId=(Long) session.getAttribute("userId");
			cartOrder.deleteAllByUserId(userId);
			order_repo.save(order_list);
			return "redirect:/orders";
		}
		order_list.setStatus("failed");
		order_repo.save(order_list);
		return "redirect:/orders";
	}
	
	@GetMapping("/paymentFailed")
	public String paymentFail(HttpSession session) {
		Order1  order_list=(Order1) session.getAttribute("order_list");
		order_list.setStatus("failed");
		order_repo.save(order_list);
		return "redirect:/orders";
	}

}
