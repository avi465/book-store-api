package com.example.bookstore_api.service;

import com.example.bookstore_api.dto.UserDTO;
import com.example.bookstore_api.exception.NotFoundException;
import com.example.bookstore_api.mapper.UserMapper;
import com.example.bookstore_api.model.User;
import com.example.bookstore_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    public UserDTO registerUser(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User savedUser = userRepository.save(userMapper.toUser(userDTO));
        return userMapper.toUserDTO(savedUser);
    }

    public UserDTO findByUsername(String username) {
        User foundUser = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
        return userMapper.toUserDTO(foundUser);
    }

    public User updateUserDetails(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setAddress(userDetails.getAddress());
        return userRepository.save(user);
    }
}
