package com.example.Dukkan.Repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Dukkan.Model.Order1;

public interface OrderRepository extends JpaRepository<Order1, Long>{

	List<Order1> findAllByUserId(Long userId, Sort sort);
	
}
