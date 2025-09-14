package com.project.service;

import com.project.entity.Order;
import java.util.List;

public interface OrderService {
	
	// create an order 
    Order createOrder(Order order);
    
    //see all the order details
    List<Order> getAllOrders();
    
    // get all the order by id
    Order getOrderById(Long id);
    
    //get the order by customer name
    List<Order> getOrdersByCustomerName(String customerName);
}
