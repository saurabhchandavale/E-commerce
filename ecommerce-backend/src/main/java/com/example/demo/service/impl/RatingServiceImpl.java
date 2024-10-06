package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ProductException;
import com.example.demo.model.Product;
import com.example.demo.model.Rating;
import com.example.demo.model.User;
import com.example.demo.repository.RatingRepository;
import com.example.demo.request.RatingRequest;
import com.example.demo.service.ProductService;
import com.example.demo.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {
	@Autowired
	private RatingRepository ratingRepository;
	@Autowired
	private ProductService productService;

	@Override
	public Rating createRating(RatingRequest request, User user) throws ProductException {
		Product product = productService.findProductById(request.getProductId());
		
		Rating rating = new Rating();
		rating.setProduct(product);
		rating.setCreatedAt(LocalDateTime.now());
		rating.setRating(request.getRating());
		rating.setUser(user);
		
		Rating savedRating = ratingRepository.save(rating);
		
		return savedRating;
	}

	@Override
	public List<Rating> getProductsRating(Long productId) {
		List<Rating> allProductsRating = ratingRepository.getAllProductsRating(productId);
		return allProductsRating;
	}

}
