package com.example.bricoli.mapper;


import com.example.bricoli.dto.AdminDto;
import com.example.bricoli.models.Admin;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    Admin toEntity(AdminDto dto);
    AdminDto toDTO(Admin entity);
    List<AdminDto> toDTOList(List<Admin> admins);
    List<Admin> toEntityList(List<AdminDto> adminDtos);
}
