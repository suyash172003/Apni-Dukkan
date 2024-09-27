package com.example.Dukkan.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Dukkan.Model.Register;

public interface RegisterRepository extends JpaRepository<Register, Long> {
	Register findByEmail(String email);
	boolean existsByEmail(String email);
	
}
