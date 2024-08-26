package com.shop.service;

import com.shop.entity.Order;
import com.shop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getOrdersByDate(LocalDate date) {
        return orderRepository.findByDate(date);
    }

    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public List<Order> getOrdersByProductId(Long productId) {
        return orderRepository.findByProductId(productId);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}

















//package com.shop.service;
//
//import com.shop.entity.Order;
//import com.shop.repository.OrderRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class OrderService {
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    public List<Order> getAllOrders() {
//        return orderRepository.findAll();
//    }
//
//    public Optional<Order> getOrderById(Long id) {
//        return orderRepository.findById(id);
//    }
//
//    public List<Order> getOrdersByDate(LocalDate date) {
//        return orderRepository.findByDate(date);
//    }
//
//    public List<Order> getOrdersByCustomerId(Long customerId) {
//        return orderRepository.findByCustomerId(customerId);
//    }
//
//    public List<Order> getOrdersByProductId(Long productId) {
//        return orderRepository.findByProductId(productId);
//    }
//
//    public Order saveOrder(Order order) {
//        return orderRepository.save(order);
//    }
//
//    public void deleteOrder(Long id) {
//        orderRepository.deleteById(id);
//    }
//
//}
