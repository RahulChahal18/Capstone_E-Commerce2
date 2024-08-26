package com.shop.controller;


import com.shop.dto.OrderDTO;
import com.shop.entity.Customer;
import com.shop.entity.Order;
import com.shop.entity.Product;
import com.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shop/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderDTO orderDTO) {
        // Convert DTO to entity
        Order order = convertToEntity(orderDTO);

        // Save the order
        Order savedOrder = orderService.saveOrder(order);

        // Convert the saved entity back to DTO
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedOrder));
    }

    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        Optional<Order> orderOpt = orderService.getOrderById(id);
        if (orderOpt.isPresent()) {
            return ResponseEntity.ok(convertToDTO(orderOpt.get()));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found with ID: " + id);
        }
    }

    @GetMapping("/findByDate")
    public ResponseEntity<List<OrderDTO>> getOrdersByDate(@RequestParam String date) {
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            List<OrderDTO> orders = orderService.getOrdersByDate(parsedDate).stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(orders);
        }
        catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    @GetMapping("/findByCustomerId")
    public List<OrderDTO> getOrdersByCustomerId(@RequestParam Long customerId) {
        return orderService.getOrdersByCustomerId(customerId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/findByProductId")
    public List<OrderDTO> getOrdersByProductId(@RequestParam Long productId) {
        return orderService.getOrdersByProductId(productId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (orderService.getOrderById(id).isPresent()) {
            orderService.deleteOrder(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private OrderDTO convertToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setOId(order.getOId());
        dto.setDate(order.getDate());
        dto.setCustomerId(order.getCustomer() != null ? order.getCustomer().getId() : null);
        dto.setProductId(order.getProduct() != null ? order.getProduct().getId() : null);
        return dto;
    }

    private Order convertToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOId(orderDTO.getOId());
        order.setDate(orderDTO.getDate());

        // Assuming the services to fetch customer and product by ID
        Customer customer = new Customer();
        customer.setId(orderDTO.getCustomerId());
        order.setCustomer(customer);

        Product product = new Product();
        product.setId(orderDTO.getProductId());
        order.setProduct(product);

        return order;
    }
}

















































//package com.shop.controller;
//
//import com.shop.entity.Order;
//import com.shop.service.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/shop/orders")
//public class OrderController {
//
//    @Autowired
//    private OrderService orderService;
//
//    @GetMapping
//    public List<Order> getAllOrders() {
//        return orderService.getAllOrders();
//    }
//
//    @GetMapping("/{id}")
//    public Optional<Order> getOrderById(@PathVariable Long id) {
//        return orderService.getOrderById(id);
//    }
//
//    @GetMapping("/findByDate")
//    public List<Order> getOrdersByDate(@RequestParam LocalDate date) {
//        return orderService.getOrdersByDate(date);
//    }
//
//    @GetMapping("/findByCustomerId")
//    public List<Order> getOrdersByCustomerId(@RequestParam Long customerId) {
//        return orderService.getOrdersByCustomerId(customerId);
//    }
//
//    @GetMapping("/findByProductId")
//    public List<Order> getOrdersByProductId(@RequestParam Long productId) {
//        return orderService.getOrdersByProductId(productId);
//    }
//
//    @PostMapping
//    public Order saveOrder(@RequestBody Order order) {
//        return orderService.saveOrder(order);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteOrder(@PathVariable Long id) {
//        orderService.deleteOrder(id);
//    }
//}
