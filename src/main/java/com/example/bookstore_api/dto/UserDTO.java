package com.example.bookstore_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;

    @NotEmpty(message = "Username should not be empty")
    @Email(message = "Username should be of type email")
    private String username;

    @NotEmpty(message = "Password should not be empty")
    @Size(message = "Password must be at least 6 character long", min = 6)
    private String password;

    @Email(message = "Email should be of type email")
    private String email;

    private String name;
    private String address;
}
