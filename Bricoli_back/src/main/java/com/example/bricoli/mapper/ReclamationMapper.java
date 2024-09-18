package com.example.bricoli.mapper;

import com.example.bricoli.dto.PrestataireDto;
import com.example.bricoli.dto.ReclamationDto;
import com.example.bricoli.models.Prestataire;
import com.example.bricoli.models.Reclamation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface ReclamationMapper {
    @Mapping(source = "prestataireId",target = "prestataire.id")
    @Mapping(source = "clientId",target = "client.id")

    Reclamation toEntity(ReclamationDto DTO);
    @Mapping(source = "prestataire.id",target = "prestataireId")
    @Mapping(source = "client.id",target = "clientId")

    ReclamationDto toDTO(Reclamation entity);
    List<ReclamationDto> toDTOList(List<Reclamation> Reclamations);
    List<Reclamation> toEntityList(List<ReclamationDto> ReclamationDto);

}
