package com.example.bricoli.mapper;

import com.example.bricoli.dto.PersonneDto;
import com.example.bricoli.dto.PrestataireDto;
import com.example.bricoli.models.Personne;
import com.example.bricoli.models.Prestataire;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")

public interface PrestataireMapper {
    Prestataire toEntity(PrestataireDto DTO);
    PrestataireDto toDTO(Prestataire entity);
    List<PrestataireDto> toDTOList(List<Prestataire> Prestataires);
    List<Prestataire> toEntityList(List<PrestataireDto> PrestataireDtos);
}
