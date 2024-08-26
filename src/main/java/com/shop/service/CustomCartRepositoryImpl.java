package com.shop.service;

import java.util.List;

import com.shop.entity.Cart;
import com.shop.repository.CustomCartRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

public class CustomCartRepositoryImpl implements CustomCartRepository {

	@PersistenceContext
	private EntityManager em;
	@Override
	public Cart findByCustomerId(long customerId) {
		String getCartByCustId = "SELECT * FROM cart where customer_id="+customerId;
		Query q = em.createNativeQuery(getCartByCustId, Cart.class);
		List<Cart> carts = q.getResultList();
		if(carts.size()==0) {
			return null;
		}
		System.out.println("******"+carts.get(0).getClass());
		Cart temp = (Cart)carts.get(0);
		return temp;
	}

}
