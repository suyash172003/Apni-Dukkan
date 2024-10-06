package com.example.Dukkan.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cart {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String ordername;
	private String quantity;
	private Long price;
    private String image;
    private Long userId;
    private Long orderId;
    
    public Cart() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrdername() {
		return ordername;
	}

	public void setOrdername(String ordername) {
		this.ordername = ordername;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Cart(Long id, String ordername, String quantity, Long price, String image, Long userId, Long orderId) {
		super();
		this.id = id;
		this.ordername = ordername;
		this.quantity = quantity;
		this.price = price;
		this.image = image;
		this.userId = userId;
		this.orderId = orderId;
	}

	
}