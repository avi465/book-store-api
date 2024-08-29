package com.example.bookstore_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String bookName;
    private String author;
    private String description;
    private String quantity;
    private Double price;
    private Double discountPrice;
    private String bookImage;
}
