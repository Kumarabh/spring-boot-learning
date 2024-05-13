package com.boot.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.application.dao.CartRepository;
import com.boot.application.entities.Cart;

@Service
public class CartService {

	@Autowired
	CartRepository cartRepository;
	
	public List<Cart> getAll() {
		return this.cartRepository.findAll();
	}
	
	public Cart getById(int id) {
		return this.cartRepository.findById(id).get();
	}
	
	public Cart create(Cart c) {
		return this.cartRepository.save(c);
	}
	
	public Cart update(Cart c) {
		return this.cartRepository.save(c);
	}
	
	public void deleteById(int id) {
		this.cartRepository.deleteById(id);
		
	}
}
