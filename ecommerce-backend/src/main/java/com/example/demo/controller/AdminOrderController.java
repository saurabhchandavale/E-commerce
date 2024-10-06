package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.OrderException;
import com.example.demo.model.Order;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.OrderService;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/")
	public ResponseEntity<List<Order>> getAllOrdersHandler(){
		List<Order> orders = orderService.getAllOrders();
		return new ResponseEntity<List<Order>>(orders,HttpStatus.OK);
		
	}
	@PutMapping("/{orderId}/ship")
	public ResponseEntity<Order> confirmedOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt) throws OrderException{
		Order confirmedOrder = orderService.confirmedOrder(orderId);
		return new ResponseEntity<>(confirmedOrder,HttpStatus.OK);
	}
	
	@GetMapping("/{orderId}/deliver")
	public ResponseEntity<Order> deliveredOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt) throws OrderException{
		Order deliveredOrder = orderService.deliveredOrder(orderId);
		return new ResponseEntity<>(deliveredOrder,HttpStatus.OK);
	}
	
	@GetMapping("/{orderId}/cancel")
	public ResponseEntity<Order> cancelOrderhandler(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt) throws OrderException{
		Order cancelledOrder = orderService.cancelledOrder(orderId);
		return new ResponseEntity<>(cancelledOrder,HttpStatus.OK);
	}
	
	@DeleteMapping("/{orderId}/delete")
	public ResponseEntity<ApiResponse> deleteOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt) throws OrderException{
		orderService.deleteOrder(orderId);
		ApiResponse response = new ApiResponse();
		response.setMessage("Order deleted successfully... ");
		response.setStatus(true);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	
	

}
