package com.shop.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class OrderDTO {
    private Long oId;
    private LocalDate date;
    private Long customerId;
    private Long productId;
}


