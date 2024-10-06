package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	
	@Query("SELECT c from Cart c WHERE c.user.id = : userId")
	public Cart findCartByUserId(@Param("userId") Long userId);

}
