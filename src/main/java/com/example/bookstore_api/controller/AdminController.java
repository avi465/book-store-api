package com.example.bookstore_api.controller;

import com.example.bookstore_api.model.Admin;
import com.example.bookstore_api.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.registerAdmin(admin));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginAdmin(@RequestBody Admin admin){
        Admin existingAdmin = adminService.findByUsername(admin.getUsername());
        if ((existingAdmin != null && admin.getPassword().equals(existingAdmin.getPassword()))){
            return ResponseEntity.ok("JWT_TOKEN");
        }
        return  ResponseEntity.status(401).body("Invalid credentials");
    }
}
