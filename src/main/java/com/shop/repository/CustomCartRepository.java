package com.shop.repository;

import com.shop.entity.Cart;

public interface CustomCartRepository {
    Cart findByCustomerId(long customerId);
}
