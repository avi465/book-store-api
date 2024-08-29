package com.example.bookstore_api.service;

import com.example.bookstore_api.dto.AdminDTO;
import com.example.bookstore_api.exception.NotFoundException;
import com.example.bookstore_api.mapper.AdminMapper;
import com.example.bookstore_api.model.Admin;
import com.example.bookstore_api.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdminMapper adminMapper;

    public AdminDTO registerAdmin(AdminDTO adminDTO){
        adminDTO.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
        Admin savedAdmin = adminRepository.save(adminMapper.toAdmin(adminDTO));
        return adminMapper.toAdminDTO(savedAdmin);
    }

    public AdminDTO findByUsername(String username){
        Admin foundAdmin = adminRepository.findByUsername(username).orElseThrow(()-> new NotFoundException("Admin not found"));
        return adminMapper.toAdminDTO(foundAdmin);
    }
}
