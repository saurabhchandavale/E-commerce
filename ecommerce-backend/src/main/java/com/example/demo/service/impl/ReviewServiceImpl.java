package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ProductException;
import com.example.demo.model.Product;
import com.example.demo.model.Review;
import com.example.demo.model.User;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.request.ReviewRequest;
import com.example.demo.service.ProductService;
import com.example.demo.service.ReviewService;
@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private ProductService productService;

	@Override
	public Review createReview(ReviewRequest request, User user) throws ProductException {
		Product product = productService.findProductById(request.getProductId());
		
		Review review = new Review();
		review.setProduct(product);
		review.setReview(request.getReview());
		review.setUser(user);
		review.setCreatedAt(LocalDateTime.now());
		
		Review savedReview = reviewRepository.save(review);
		return savedReview;
	}

	@Override
	public List<Review> getAllProductsReview(Long productId) {
		return reviewRepository.getAllProductsReview(productId);
	}

}
