package com.shop.service;

import com.shop.entity.LineItem;
import com.shop.repository.LineItemRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineItemService {

    @Autowired
    private LineItemRepository lineItemRepository;

    public LineItem save(LineItem lineItem) {
        return lineItemRepository.save(lineItem);
    }

    public LineItem findById(Long id) {
        return lineItemRepository.findById(id).orElse(null);
    }

    public List<LineItem> findAll() {
        return lineItemRepository.findAll();
    }

    public void delete(Long id) {
        lineItemRepository.deleteById(id);
    }
}
