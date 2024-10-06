package com.example.demo.service;

import java.util.List;

import com.example.demo.exception.ProductException;
import com.example.demo.model.Rating;
import com.example.demo.model.User;
import com.example.demo.request.RatingRequest;

public interface RatingService {
	
	public Rating createRating(RatingRequest request, User user) throws ProductException;
	public List<Rating> getProductsRating(Long productId);

}
