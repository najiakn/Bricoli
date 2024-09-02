package com.example.bricoli.mapper;


import com.example.bricoli.dto.PersonneDto;
import com.example.bricoli.models.Personne;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonneMapper {
    Personne toEntity(PersonneDto DTO);
    PersonneDto toDTO(Personne entity);
    List<PersonneDto> toDTOList(List<Personne> personnes);
    List<Personne> toEntityList(List<PersonneDto> personneDtos);
}
