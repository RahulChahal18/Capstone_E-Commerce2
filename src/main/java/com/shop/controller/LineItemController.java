package com.shop.controller;

import com.shop.entity.LineItem;
import com.shop.service.LineItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lineItems")
public class LineItemController {
    @Autowired
    private LineItemService lineItemService;

    @PostMapping
    public LineItem createLineItem(@RequestBody LineItem lineItem) {
        return lineItemService.save(lineItem);
    }
}
