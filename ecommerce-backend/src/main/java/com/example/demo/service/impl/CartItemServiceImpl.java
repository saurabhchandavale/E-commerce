package com.example.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.CartItemException;
import com.example.demo.exception.UserException;
import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CartItemService;
import com.example.demo.service.UserService;

@Service
public class CartItemServiceImpl implements CartItemService {
	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private CartRepository cartRepository;

	@Override
	public CartItem createCartItem(CartItem cartItem) {
		cartItem.setQuantity(1);
		cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
		cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantity());
		CartItem savedCartItem = cartItemRepository.save(cartItem);
		return savedCartItem;
	}

	@Override
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
		CartItem item = findCartItemById(id);
		User user = userService.findUserById(item.getUserId());
		if (user.getId().equals(userId)) {
			System.out.println("SCE32 ------" + cartItem.getQuantity());
			item.setQuantity(cartItem.getQuantity());
			item.setPrice(item.getProduct().getPrice() * item.getQuantity());
			item.setDiscountedPrice(item.getProduct().getDiscountedPrice() * item.getQuantity());
		}
		CartItem updatedCartItem = cartItemRepository.save(item);
		return updatedCartItem;
	}

	@Override
	public CartItem isCartItemExists(Cart cart, Product product, String size, Long userId) {
		CartItem cartItemExists = cartItemRepository.isCartItemExists(cart, product, size, userId);
		return cartItemExists;
	}

	@Override
	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
		CartItem cartItemById = findCartItemById(cartItemId);
		User reqUser = userService.findUserById(userId);
		User user = userService.findUserById(cartItemById.getUserId());

		if (user.getId().equals(reqUser.getId())) {
			cartItemRepository.deleteById(cartItemId);
		} else {
			throw new UserException("You are not allowed to remove other users item... ");
		}
	}

	@Override
	public CartItem findCartItemById(Long cartItemId) throws CartItemException {
		Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
		if (cartItem.isPresent()) {
			return cartItem.get();
		} else {
			throw new CartItemException("CartItem not found with id " + cartItemId);
		}
	}

}
