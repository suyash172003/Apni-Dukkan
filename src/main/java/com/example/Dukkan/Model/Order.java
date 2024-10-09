package com.example.Dukkan.Model;

public class Order {
	
	private Double total;
	private String currency;
	private String method; 
	private String intent;
	private String description;
	private String cancelUrl;
	private String successUrl;
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getIntent() {
		return intent;
	}
	public void setIntent(String intent) {
		this.intent = intent;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCancelUrl() {
		return cancelUrl;
	}
	public void setCancelUrl(String cancelUrl) {
		this.cancelUrl = cancelUrl;
	}
	public String getSuccessUrl() {
		return successUrl;
	}
	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}
	public Order(Double total, String currency, String method, String intent, String description, String cancelUrl,
			String successUrl) {
		super();
		this.total = total;
		this.currency = currency;
		this.method = method;
		this.intent = intent;
		this.description = description;
		this.cancelUrl = cancelUrl;
		this.successUrl = successUrl;
	}
	public Order() {
	}
}
