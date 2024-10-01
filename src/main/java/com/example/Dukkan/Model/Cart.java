package com.example.Dukkan.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Cart {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String ordername;
	private String quantity;
	private long price;
    private String image;
    private Long userId;
	
	@ManyToOne
	@JoinColumn
	private Register register;
	
	
	public Cart(long id, String ordername, String quantity, long price, String image, Long userId, Register register) {
		super();
		this.id = id;
		this.ordername = ordername;
		this.quantity = quantity;
		this.price = price;
		this.image = image;
		this.userId = userId;
		this.register = register;
	}
	public Cart() {}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Register getRegister() {
		return register;
	}
	public void setRegister(Register register) {
		this.register = register;
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
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}