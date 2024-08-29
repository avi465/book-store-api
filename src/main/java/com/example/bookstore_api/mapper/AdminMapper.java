package com.example.bookstore_api.mapper;

import com.example.bookstore_api.dto.AdminDTO;
import com.example.bookstore_api.model.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    @Mapping(target = "password", ignore = true)
    AdminDTO toAdminDTO(Admin admin);
    Admin toAdmin(AdminDTO adminDTO);
}
