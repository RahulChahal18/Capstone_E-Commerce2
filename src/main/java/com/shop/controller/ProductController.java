package com.shop.controller;

import com.shop.entity.Product;
import com.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shop/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/findByProdName")
    public ResponseEntity<List<Product>> getProductsByProdName(@RequestParam String prodName) {
        List<Product> products = productService.getProductsByProdName(prodName);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/findByCategory")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam String category) {
        List<Product> products = productService.getProductsByCategory(category);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/findByPriceGreaterThan")
    public ResponseEntity<List<Product>> getProductsByPriceGreaterThan(@RequestParam Double price) {
        List<Product> products = productService.getProductsByPriceGreaterThan(price);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productService.getProductById(id).isPresent()) {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}



























//package com.shop.controller;
//
//import com.shop.entity.Product;
//import com.shop.service.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/shop/products")
//public class ProductController {
//
//    @Autowired
//    private ProductService productService;
//
//    @GetMapping
//    public List<Product> getAllProducts() {
//        return productService.getAllProducts();
//    }
//
//    @GetMapping("/{id}")
//    public Optional<Product> getProductById(@PathVariable Long id) {
//        return productService.getProductById(id);
//    }
//
//    @GetMapping("/findByProdName")
//    public List<Product> getProductsByProdName(@RequestParam String prodName) {
//        return productService.getProductsByProdName(prodName);
//    }
//
//    @GetMapping("/findByCategory")
//    public List<Product> getProductsByCategory(@RequestParam String category) {
//        return productService.getProductsByCategory(category);
//    }
//
//    @GetMapping("/findByPriceGreaterThan")
//    public List<Product> getProductsByPriceGreaterThan(@RequestParam Double price) {
//        return productService.getProductsByPriceGreaterThan(price);
//    }
//
//    @PostMapping
//    public Product saveProduct(@RequestBody Product product) {
//        return productService.saveProduct(product);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteProduct(@PathVariable Long id) {
//        productService.deleteProduct(id);
//    }
//}
