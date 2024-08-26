package com.shop.service;

import com.shop.entity.Cart;
import com.shop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

	public Cart getCartByCustomer_Id(Long id) {
		return cartRepository.findByCustomerId(id);
	}

	public Cart save(Cart cart) {
		return cartRepository.save(cart);
	}
}
