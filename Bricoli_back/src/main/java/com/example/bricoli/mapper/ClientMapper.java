package com.example.bricoli.mapper;

import com.example.bricoli.dto.ClientDto;
import com.example.bricoli.dto.PersonneDto;
import com.example.bricoli.models.Client;
import com.example.bricoli.models.Personne;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface ClientMapper {
    Client toEntity(ClientDto DTO);
    ClientDto toDTO(Client entity);
    List<ClientDto> toDTOList(List<Client> Clients);
    List<Client> toEntityList(List<ClientDto> ClientDtos);
}
