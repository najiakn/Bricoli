package com.example.bricoli.mapper;

import com.example.bricoli.dto.ReclamationDto;
import com.example.bricoli.dto.ServiceDto;
import com.example.bricoli.models.Reclamation;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Mapper(componentModel = "spring")

public interface ServiceMapper {

    Service toEntity(ServiceDto DTO);
    ServiceDto toDTO(Service entity);
    List<ServiceDto> toDTOList(List<Service> Services);
    List<Service> toEntityList(List<ServiceDto> ServiceDtos);

}
