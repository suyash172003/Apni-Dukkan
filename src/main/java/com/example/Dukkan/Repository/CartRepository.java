package com.example.Dukkan.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Dukkan.Model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{
}
