package com.shop.dto;

import lombok.Data;

@Data
public class LineItemDTO {
    private Long id;
    private Long productId;
    private int quantity;
    private double itemTotal;
}

