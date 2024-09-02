package com.example.bricoli.mapper;

import com.example.bricoli.dto.ServiceDto;
import com.example.bricoli.dto.TypeServiceDto;
import com.example.bricoli.models.TypeService;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper(componentModel = "spring")

public interface TypeServiceMapper {

    TypeService toEntity(TypeServiceDto DTO);
    TypeServiceDto toDTO(TypeService entity);
    List<TypeServiceDto> toDTOList(List<TypeService> TypeServices);
    List<TypeService> toEntityList(List<TypeServiceDto> TypeServiceDtos);
}
