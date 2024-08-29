package com.example.bookstore_api.mapper;

import com.example.bookstore_api.dto.AdminDTO;
import com.example.bookstore_api.dto.UserDTO;
import com.example.bookstore_api.model.Admin;
import com.example.bookstore_api.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    UserDTO toUserDTO(User user);
    User toUser(UserDTO userDTO);
}
