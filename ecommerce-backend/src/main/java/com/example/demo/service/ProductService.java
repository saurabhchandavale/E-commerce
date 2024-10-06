package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.exception.ProductException;
import com.example.demo.model.Product;
import com.example.demo.request.CreateProductRequest;

public interface ProductService {

	
	public Product createProduct(CreateProductRequest request);
		
	public String deleteProduct(Long ProductId) throws ProductException;
	
	public Product updateProduct(Long ProductId, Product request) throws ProductException;
	
	public Product findProductById(Long productId) throws ProductException;
	
	public List<Product> findProductsByCategory(String category) throws ProductException;
	
	public Page<Product> getAllProduct(String category, List<String> color, List<String> size, Integer minPrice, Integer maxPrice,
			Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);

	public List<Product> findAllProducts();
	
	public List<Product> recentlyAddedProduct();
	
	
}
