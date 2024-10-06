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
import com.example.demo.model.Rating;
import com.example.demo.model.User;
import com.example.demo.request.RatingRequest;
import com.example.demo.service.RatingService;
import com.example.demo.service.UserService;
@RestController
@RequestMapping("/api/rating")
public class RatingController {
	@Autowired
	private RatingService ratingService;
	@Autowired
	private UserService userService;
	@PostMapping("/create")
	public ResponseEntity<Rating> createRating(@RequestBody RatingRequest request, 
            @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
		User user = userService.findUserProfileByJwt(jwt);
		Rating rating = ratingService.createRating(request, user);
		return new ResponseEntity<Rating>(rating,HttpStatus.CREATED);
		
	}
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Rating>> getAllProductsRating(@RequestHeader("Authorization") String jwt, @PathVariable Long productId) throws UserException{
		User user = userService.findUserProfileByJwt(jwt);
		List<Rating> productsRating = ratingService.getProductsRating(productId);
		return new ResponseEntity<List<Rating>>(productsRating,HttpStatus.OK);
	}
}
