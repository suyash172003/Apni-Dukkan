package com.example.Dukkan.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Dukkan.Model.Order;
import com.example.Dukkan.Service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import jakarta.servlet.http.HttpSession;

@Controller
public class PayPalController {
	
	@Autowired
	PaypalService service;
	
	@PostMapping("/payment")
	public String createPayment(@ModelAttribute("order") Order order,HttpSession session) throws PayPalRESTException {
		Payment payment=service.createPayment(order.getTotal(), order.getCurrency(),order.getMethod(), order.getIntent(), order.getDescription(),order.getCancelUrl(),order.getSuccessUrl());
		for(Links link:payment.getLinks()) {
			if(link.getRel().equals("approval_url")) {
				return "redirect:"+link.getHref();
			}
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/paymentSuccess")
	public String paymentSuccess(@RequestParam String paymentId,@RequestParam String PayerID) throws PayPalRESTException {
		System.out.println("Payment ID: " + paymentId);
	    System.out.println("Payer ID: " + PayerID);
	    
		Payment payment=service.executePayment(paymentId, PayerID);
		System.out.println(payment.toJSON());
		if(payment.getState().equals("approved")) {
			return "success";
		}
		return "redirect:/";
	}
	
	@GetMapping("/paymentFailed")
	public String paymentFail() {
		return "cancel";
	}

}
