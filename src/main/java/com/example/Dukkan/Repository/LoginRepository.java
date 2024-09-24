package com.example.Dukkan.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Dukkan.Model.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {

}
