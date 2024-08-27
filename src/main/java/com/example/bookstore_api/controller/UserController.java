package com.example.bookstore_api.controller;

import com.example.bookstore_api.model.User;
import com.example.bookstore_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        User existingUser = userService.findByUsername(user.getUsername());
        if (existingUser != null && user.getPassword().equals(existingUser.getPassword())) {
            // Assuming JWT token creation here
            return ResponseEntity.ok("JWT_TOKEN");
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserDetails(@PathVariable Long id, @RequestBody User userDetails) {
        return ResponseEntity.ok(userService.updateUserDetails(id, userDetails));
    }
}
