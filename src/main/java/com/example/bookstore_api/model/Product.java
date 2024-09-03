package com.example.bookstore_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Book should not be empty")
    private String bookName;

    @NotEmpty(message = "Author should not be empty")
    private String author;

    @NotEmpty(message = "Description should not be empty")
    private String description;

    @NotEmpty(message = "Quantity should not be empty")
    private String quantity;

    @NotNull(message = "Price should not be null")
    private Double price;

    private Double discountPrice;
    private String bookImage;
}
