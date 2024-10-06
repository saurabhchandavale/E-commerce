package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ProductException;
import com.example.demo.exception.UserException;
import com.example.demo.model.Review;
import com.example.demo.model.User;
import com.example.demo.request.ReviewRequest;
import com.example.demo.service.ReviewService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
	@Autowired
	public UserService userService;
	@Autowired
	public ReviewService reviewService;
	
	@PostMapping("/create")
	public ResponseEntity<Review> createReview(@RequestHeader("Authorization") String jwt, @RequestBody ReviewRequest request) throws UserException, ProductException{
		User user = userService.findUserProfileByJwt(jwt);
		Review review = reviewService.createReview(request, user);
		return new ResponseEntity<Review>(review,HttpStatus.CREATED);
		
	}
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Review>> getAllProductsReview(@PathVariable Long productId, @RequestHeader("Authorization") String jwt) throws UserException{
		User user = userService.findUserProfileByJwt(jwt);
		List<Review> allProductsReview = reviewService.getAllProductsReview(productId);
		
		return new ResponseEntity<List<Review>>(allProductsReview, HttpStatus.OK);
	}
	

}
