package com.example.bricoli.mapper;

import com.example.bricoli.dto.ServiceDto;
import com.example.bricoli.models.ServiceModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Mapper(componentModel = "spring")

public interface ServiceMapper {

    ServiceModel toEntity(ServiceDto DTO);
    ServiceDto toDTO(ServiceModel entity);
    List<ServiceDto> toDTOList(List<ServiceModel> Services);
    List<ServiceModel> toEntityList(List<ServiceDto> ServiceDtos);

}
