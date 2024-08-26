package com.shop.repository;

import com.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProdName(String prodName);
    List<Product> findByCategory(String category);
    List<Product> findByPriceGreaterThan(Double price);
}
