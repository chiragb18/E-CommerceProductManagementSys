package com.project.service;

import com.project.entity.Order;
import com.project.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    
    // create an order here
    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
    
    
    // get all orders here
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    
    // get order by id
    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }
    
    // get order by customer name
    @Override
    public List<Order> getOrdersByCustomerName(String customerName) {
        return orderRepository.findByCustomerName(customerName);
    }
}
