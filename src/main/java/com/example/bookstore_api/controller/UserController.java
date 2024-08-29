package com.example.bookstore_api.controller;

import com.example.bookstore_api.dto.UserDTO;
import com.example.bookstore_api.model.User;
import com.example.bookstore_api.service.JwtService;
import com.example.bookstore_api.service.UserService;
import com.example.bookstore_api.util.LoginResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO response = userService.registerUser(userDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserDTO userDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword())
        );
        final String jwt = jwtService.generateToken(userDTO.getUsername());
        return new ResponseEntity<>(new LoginResponse("Logged in successfully", jwt), HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserDetails(@Valid @PathVariable Long id, @RequestBody User userDetails) {
        return ResponseEntity.ok(userService.updateUserDetails(id, userDetails));
    }
}
