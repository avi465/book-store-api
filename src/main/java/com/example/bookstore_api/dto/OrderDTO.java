package com.example.bookstore_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private String username;
    private LocalDateTime orderDate;
    private double totalAmount;
    private String status;
    private List<OrderItemDTO> items;
}
