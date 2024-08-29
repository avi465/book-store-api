package com.example.bookstore_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishlistItemDTO {
    private Long id;
    private Long productId;
    private String productName;
    private double price;
}
