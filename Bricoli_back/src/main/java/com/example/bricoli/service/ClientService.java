package com.example.bricoli.service;

import com.example.bricoli.dto.ClientDto;
import com.example.bricoli.dto.PrestataireDto;

import java.util.List;

public interface ClientService {
    ClientDto create (ClientDto clientDto);
    ClientDto update (int id, ClientDto clientDto);
    void delete (int id);
    List<ClientDto> getAll();
    ClientDto getById(int id);
}
