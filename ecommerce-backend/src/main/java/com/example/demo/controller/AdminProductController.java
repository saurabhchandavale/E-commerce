package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ProductException;
import com.example.demo.model.Product;
import com.example.demo.request.CreateProductRequest;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {
	@Autowired
	private ProductService productService;
	
	@PostMapping("/create")
	public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request){
		Product createdProduct = productService.createProduct(request);
		return new ResponseEntity<Product>(createdProduct,HttpStatus.CREATED);
		
	}
	@DeleteMapping("/{productId}/delete")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) throws ProductException{
		productService.deleteProduct(productId);
		ApiResponse response = new ApiResponse();
		response.setMessage("Product deleted successfully... ");
		response.setStatus(true);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Product>> findAllProducts(){
		List<Product> allProducts = productService.findAllProducts();
		return new ResponseEntity<List<Product>>(allProducts,HttpStatus.OK);
	}
	
	@PutMapping("/{productId}/update")
	public ResponseEntity<Product> updateProduct(@RequestBody Product request, @PathVariable Long productId) throws ProductException{
		System.out.println("Update controller...");
		Product updateProduct = productService.updateProduct(productId, request);
		return new ResponseEntity<Product>(updateProduct,HttpStatus.OK);
		
		
	}
	@PostMapping("/creats")
	public ResponseEntity<ApiResponse> createMultipleProducts(@RequestBody CreateProductRequest[] request){
		for(CreateProductRequest product : request) {
			productService.createProduct(product);
		}
		ApiResponse response = new ApiResponse();
		response.setMessage("Products created Successfully... ");
		response.setStatus(true);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	@GetMapping("/recent")
	public ResponseEntity<List<Product>> recentlyAddedProducts(){
		List<Product> recentlyAddedProduct = productService.recentlyAddedProduct();
		return new ResponseEntity<List<Product>>(recentlyAddedProduct,HttpStatus.OK);
	}
}
