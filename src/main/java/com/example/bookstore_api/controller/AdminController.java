package com.example.bookstore_api.controller;

import com.example.bookstore_api.dto.AdminDTO;
import com.example.bookstore_api.service.AdminService;
import com.example.bookstore_api.service.JwtService;
import com.example.bookstore_api.util.LoginResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AdminDTO> registerAdmin(@Valid @RequestBody AdminDTO adminDTO) {
        AdminDTO response = adminService.registerAdmin(adminDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@Valid @RequestBody AdminDTO adminDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(adminDTO.getUsername(), adminDTO.getPassword())
        );
        final String jwt = jwtService.generateToken(adminDTO.getUsername());
        return new ResponseEntity<>(new LoginResponse("Logged in successfully", jwt), HttpStatus.OK);
    }
}
