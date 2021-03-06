package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.entity.Order;
import com.bookstore.service.IOrderservice;
/************************************************************************************************************************************************
 * ShoppingCartController By using the object reference of OrderService class This Controller Has fuctionallity to Make Book Order,Update Order ,
 * remove Order and confirm the order Apis.
 *
 * @author Rupesh Patil
 * @version 2.0
 * @updated 2020-05-06
 * @created 2020-04-12
 * @see {@link IOrderservice} implementation of all the required services & functionality
 *
 ************************************************************************************************************************************/
@RestController
@CrossOrigin("*")
@RequestMapping("/orders")

public class ShoppingCardController {

	@Autowired
	IOrderservice orderService;
	
	@PostMapping("/{bookId}")
	public ResponseEntity<Object> addOrder(@PathVariable("bookId") int id,@RequestParam("qty") int quantity,@RequestParam("userId") int userId){
		return orderService.makeOrder(id,quantity,userId);
	}
	@PostMapping("/user/{bookId}")
	public ResponseEntity<Object> addOrderWithLogin(@PathVariable("bookId") int id,@RequestParam("qty") int quantity,@RequestHeader String token){
		return orderService.makeOrderWithToken(id,quantity,token);
	}
	
	@DeleteMapping("/{bookId}")
	public ResponseEntity<Object> removeOrder(@PathVariable("bookId") int id){
		return orderService.cancelOrder(id);
	}
	
	@GetMapping("/cart")
	public ResponseEntity<Object> getCartList(@RequestParam("userId") int userId){
		return orderService.getCartList(userId);
	}
	@GetMapping("/user-cart")
	public ResponseEntity<Object> getCartListOfUser(@RequestHeader String token){
		return orderService.getCartListWithToken(token);
	}
	@PutMapping()
	public ResponseEntity<Object> updateBookQuantity(@RequestBody Order order){
		System.out.println(order.getQuantity());
		return orderService.updateQuantity(order);
	}
	
	@PutMapping("/confirm")
	public ResponseEntity<Object> confirmOrder(@RequestBody List<Order> order,@RequestHeader String token) {
		return orderService.confirmOrder(token,order);
	}
	
}
