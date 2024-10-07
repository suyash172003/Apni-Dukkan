package com.example.Dukkan.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Dukkan.Model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{
	List<Cart> findByUserId(Long userId);
	void deleteByUserIdAndOrderId(Long userId, Long orderId); 
}
