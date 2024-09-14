package com.example.bricoli.mapper;

import com.example.bricoli.dto.ServiceDto;
import com.example.bricoli.models.ServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


import java.util.List;
@Mapper(componentModel = "spring")
public interface ServiceMapper {
    ServiceMapper INSTANCE = Mappers.getMapper(ServiceMapper.class);

    @Mapping(source ="typeService.id", target = "idType")
    ServiceDto toDTO(ServiceModel entity);

    @Mapping(source = "idType", target = "typeService.id")

    ServiceModel toEntity(ServiceDto DTO);
    List<ServiceDto> toDTOList(List<ServiceModel> Services);
    List<ServiceModel> toEntityList(List<ServiceDto> ServiceDtos);

}
