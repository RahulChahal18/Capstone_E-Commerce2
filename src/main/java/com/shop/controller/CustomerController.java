package com.shop.controller;

import com.shop.entity.Customer;
import com.shop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shop/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer customer) {
        Optional<Customer> existingCustomer = customerService.getCustomerById(id);
        if (existingCustomer.isPresent()) {
            customer.setId(id); // Ensure we're updating the correct customer
            Customer updatedCustomer = customerService.saveCustomer(customer);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/findByName")
    public ResponseEntity<List<Customer>> getCustomersByName(@RequestParam String name) {
        List<Customer> customers = customerService.getCustomersByName(name);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/findByCity")
    public ResponseEntity<List<Customer>> getCustomersByCity(@RequestParam String city) {
        List<Customer> customers = customerService.getCustomersByCity(city);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/findByPincode")
    public ResponseEntity<List<Customer>> getCustomersByPincode(@RequestParam String pincode) {
        List<Customer> customers = customerService.getCustomersByPincode(pincode);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        if (customerService.getCustomerById(id).isPresent()) {
            customerService.deleteCustomer(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}


























//package com.shop.controller;
//
//import com.shop.entity.Customer;
//import com.shop.service.CustomerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/shop/customers")
//public class CustomerController {
//
//    @Autowired
//    private CustomerService customerService;
//
//    @GetMapping
//    public List<Customer> getAllCustomers() {
//        return customerService.getAllCustomers();
//    }
//
//    @GetMapping("/{id}")
//    public Optional<Customer> getCustomerById(@PathVariable Long id) {
//        return customerService.getCustomerById(id);
//    }
//
//    @GetMapping("/findByName")
//    public List<Customer> getCustomersByName(@RequestParam String name) {
//        return customerService.getCustomersByName(name);
//    }
//
//    @GetMapping("/findByCity")
//    public List<Customer> getCustomersByCity(@RequestParam String city) {
//        return customerService.getCustomersByCity(city);
//    }
//
//    @GetMapping("/findByPincode")
//    public List<Customer> getCustomersByPincode(@RequestParam String pincode) {
//        return customerService.getCustomersByPincode(pincode);
//    }
//
//    @PostMapping
//    public Customer saveCustomer(@RequestBody Customer customer) {
//        return customerService.saveCustomer(customer);
//    }
//    @PutMapping("/{id}")
//    public Customer update(@PathVariable long id,@RequestBody Customer customer) {
//        Customer c = customerService.getCustomerById(id).get();
//        c.setId(id);
//        c.setName(customer.getName());
//        c.setCity(customer.getCity());
//        c.setPincode(customer.getPincode());
//        return customerService.saveCustomer(c);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteCustomer(@PathVariable Long id) {
//        customerService.deleteCustomer(id);
//    }
//}
