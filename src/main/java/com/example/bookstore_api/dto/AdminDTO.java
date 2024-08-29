package com.example.bookstore_api.dto;

import com.example.bookstore_api.model.Admin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {
    private Long id;

    @NotEmpty(message = "Username should not be empty")
    @Email(message = "Username should be of type email")
    private String username;

    @NotEmpty(message = "Password should not be empty")
    @Size(message = "Password must be at least 6 character long", min = 6)
    private String password;

    @Email(message = "Email should be of type email")
    private String email;
}