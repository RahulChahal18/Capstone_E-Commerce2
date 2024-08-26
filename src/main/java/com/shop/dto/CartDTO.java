package com.shop.dto;

import lombok.Data;
import java.util.List;

@Data
public class CartDTO {
    private Long id;
    private List<LineItemDTO> lineItems;
}

