package com.boot.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.application.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{

}
