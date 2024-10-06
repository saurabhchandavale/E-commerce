package com.example.demo.service;

import com.example.demo.exception.ProductException;
import com.example.demo.model.Cart;
import com.example.demo.model.User;
import com.example.demo.request.AddItemRequest;

public interface CartService {
	
	public Cart createCart(User user);
	
	public String addCartItem(Long userId, AddItemRequest request) throws ProductException;
	
	public Cart findUserCart(Long userId);

}
