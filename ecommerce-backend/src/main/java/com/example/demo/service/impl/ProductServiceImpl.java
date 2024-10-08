package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ProductException;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.request.CreateProductRequest;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private UserService userService;
	

	@Override
	public Product createProduct(CreateProductRequest request) {
		Category topLevel = categoryRepository.findByName(request.getTopLevelCategory());
		
		if(topLevel == null) {
			Category topLevelCategory = new Category();
			topLevelCategory.setName(request.getTopLevelCategory());
			topLevelCategory.setLevel(1);
			topLevel = categoryRepository.save(topLevelCategory);
		}
		
		Category secondLevel = categoryRepository.findByNameAndParent(request.getSecondLevelCategory(), topLevel.getName());
		
		if(secondLevel == null) {
			Category secondLevelCategory = new Category();
			secondLevelCategory.setName(request.getSecondLevelCategory());
			secondLevelCategory.setParentCategory(topLevel);
			secondLevelCategory.setLevel(2);
			
			secondLevel = categoryRepository.save(secondLevelCategory);
		}
		
		Category thirdLevel = categoryRepository.findByNameAndParent(request.getThirdLevelcategory(), secondLevel.getName());
		if(thirdLevel == null) {
			Category thirdLevelCategory = new Category();
			thirdLevelCategory.setName(request.getThirdLevelcategory());
			thirdLevelCategory.setParentCategory(secondLevel);
			thirdLevelCategory.setLevel(3);
			
			thirdLevel = categoryRepository.save(thirdLevelCategory);
		}
		
		Product product = new Product();
		product.setTitle(request.getTitle());
		product.setColor(request.getColor());
		product.setDescription(request.getDescription());
		product.setDiscountedPrice(request.getDiscountPresent());
		product.setDiscountPersent(request.getDiscountPresent());
		product.setImageUrl(request.getImageUrl());
		product.setBrand(request.getBrand());
		product.setPrice(request.getPrice());
		product.setSizes(request.getSize());
		product.setQuantity(request.getQuantity());
		product.setCategory(thirdLevel);
		product.setCreatedAt(LocalDateTime.now());
		
		Product savedProduct = productRepository.save(product);
		
		
		
		return savedProduct;
	}

	@Override
	public String deleteProduct(Long ProductId) throws ProductException {
		Product product = findProductById(ProductId);
		product.getSizes().clear();
		productRepository.delete(product);
		
		return "Product Deleted Successfully... ";
	}

	@Override
	public Product updateProduct(Long ProductId, Product request) throws ProductException {
		Product product = findProductById(ProductId);
		System.out.println("SCE32 Update product");
		if(request.getQuantity() != 0) {
			product.setQuantity(request.getQuantity());
		}
		product.setTitle(request.getTitle());
	    product.setDescription(request.getDescription());
	    product.setPrice(request.getPrice());
	    product.setDiscountedPrice(request.getDiscountedPrice());
	    product.setDiscountPersent(request.getDiscountPersent());
	    product.setQuantity(request.getQuantity());
	    product.setBrand(request.getBrand());
	    product.setColor(request.getColor());
	    product.setSizes(request.getSizes()); 
	    product.setImageUrl(request.getImageUrl());
		System.out.println("SCE32 ---------- " + request.getSizes());
		return productRepository.save(product);
	}

	@Override
	public Product findProductById(Long productId) throws ProductException {
		Optional<Product> product = productRepository.findById(productId);
		if(product.isPresent()) {
			return product.get();
		}
		
		throw new ProductException("Product not found with id " + productId);
	}

	@Override
	public List<Product> findProductsByCategory(String category) throws ProductException {
		return null;
	}

	@Override
	public Page<Product> getAllProduct(String category, List<String> color, List<String> size, Integer minPrice,
			Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
		Pageable pageble = PageRequest.of(pageNumber, pageSize);
		List<Product> products = productRepository.filterProducts(category, minPrice, maxPrice, minDiscount, sort);
		if(!color.isEmpty()) {
			products.stream().filter(p -> color.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor()))).collect(Collectors.toList());
		}
		if(stock!=null) {
			if(stock.equals("in_stock")){
				products = products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
			}else if(stock.equals("out_of_stock")) {
				products = products.stream().filter(p -> p.getQuantity() < 1).collect(Collectors.toList());
			}
		}
		
		int startIndex = (int) pageble.getOffset();
		int endIndex = Math.min(startIndex + pageble.getPageSize(), products.size());
		
		List<Product> pageContent = products.subList(startIndex, endIndex);
		Page<Product> filteredProducts = new PageImpl<>(pageContent,pageble,products.size());
		return filteredProducts;
	}

	@Override
	public List<Product> findAllProducts() {
		List<Product> allProducts = productRepository.findAll();
		return allProducts;
	}

	@Override
	public List<Product> recentlyAddedProduct() {
		List<Product> recentlyAddedProduct = productRepository.findTop10ByOrderByCreatedAtDesc();
		return recentlyAddedProduct;
	}

}
