package com.example.bricoli.mapper;

import com.example.bricoli.dto.PrestataireDto;
import com.example.bricoli.dto.ReclamationDto;
import com.example.bricoli.models.Prestataire;
import com.example.bricoli.models.Reclamation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface ReclamationMapper {
    Reclamation toEntity(ReclamationDto DTO);
    ReclamationDto toDTO(Reclamation entity);
    List<ReclamationDto> toDTOList(List<Reclamation> Reclamations);
    List<Reclamation> toEntityList(List<ReclamationDto> ReclamationDto);

}
