package com.example.Dukkan.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cart {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String ordername;
	private long price;
	public Cart(long id, String ordername, long price) {
		super();
		this.id = id;
		this.ordername = ordername;
		this.price = price;
	}
	public Cart() {}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOrdername() {
		return ordername;
	}
	public void setOrdername(String ordername) {
		this.ordername = ordername;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", ordername=" + ordername + ", price=" + price + "]";
	}
	
}
