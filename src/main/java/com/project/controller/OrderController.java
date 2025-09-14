package com.project.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Order;
import com.project.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	// here we connect the service method  
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    
    // add the order 
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }
     
    
    // get order by id 
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }
    
    
    // get  all the orders details
    @GetMapping("/orders")
    public ResponseEntity<?> getOrdersByCustomerName(@RequestParam String customerName) {
        List<Order> orders = orderService.getOrdersByCustomerName(customerName);
        
        if (orders.isEmpty()) {
            // Return a message if no orders found
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No orders available for customer: " + customerName);
        }
        
        return ResponseEntity.ok(orders);
    }

}
