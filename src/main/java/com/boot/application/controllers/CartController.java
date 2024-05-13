package com.boot.application.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.application.entities.Cart;
import com.boot.application.services.CartService;

@RestController
@RequestMapping("/api/v1/cart/*")
public class CartController {

	@Autowired
	CartService cartService;
	
	@GetMapping("")
	public List<Cart> getAll() {
		return this.cartService.getAll();
	}
	
	@GetMapping("{cartId}")
	public Cart getById(@PathVariable("cartId") int id) {
		return this.cartService.getById(id);
	}
	
	@PostMapping("")
	public Cart create(@RequestBody Cart c) {
		return this.cartService.create(c);
	}
	
	@PutMapping("")
	public Cart update(@RequestBody Cart c) {
		return this.cartService.update(c);
	}
	
	@DeleteMapping("{cartId}")
	public ResponseEntity<String> delete(@PathVariable("cartId") int id) {
		this.cartService.deleteById(id);
		return ResponseEntity.of(Optional.of("Deleted Successfully."));
	}
}
