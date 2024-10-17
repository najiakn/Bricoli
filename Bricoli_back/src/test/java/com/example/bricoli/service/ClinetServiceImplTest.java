package com.example.bricoli.service;

import com.example.bricoli.dto.ClientDto;
import com.example.bricoli.mapper.ClientMapper;
import com.example.bricoli.models.Client;
import com.example.bricoli.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientServiceImplTest {

    @InjectMocks
    private ClinetServiceImpl clientService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    private ClientDto clientDto;
    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        clientDto = new ClientDto();
        clientDto.setNom("John");
        clientDto.setPrenom("Doe");
        clientDto.setAge(30);
        clientDto.setGenre("Male");
        clientDto.setTelephone("123456789");
        clientDto.setVille("Beni Mellal");
        clientDto.setCnie("CN123456");

        client = new Client();
        client.setNom("John");
        client.setPrenom("Doe");
        client.setAge(30);
        client.setGenre("Male");
        client.setTelephone("123456789");
        client.setVille("Beni Mellal");
        client.setCnie("CN123456");
    }

    @Test
    void create() {
        when(clientMapper.toEntity(clientDto)).thenReturn(client);
        when(clientRepository.save(client)).thenReturn(client);
        when(clientMapper.toDTO(client)).thenReturn(clientDto);

        ClientDto createdClient = clientService.create(clientDto);

        assertNotNull(createdClient);
        assertEquals("John", createdClient.getNom());
        verify(clientMapper).toEntity(clientDto);
        verify(clientRepository).save(client);
        verify(clientMapper).toDTO(client);
    }

    @Test
    void delete() {
        int clientId = 1;

        clientService.delete(clientId);

        verify(clientRepository).deleteById(clientId);
    }

    @Test
    void getAll() {
        List<Client> clientList = new ArrayList<>();
        clientList.add(client);

        when(clientRepository.findAll()).thenReturn(clientList);
        when(clientMapper.toDTO(client)).thenReturn(clientDto);

        List<ClientDto> result = clientService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getNom());
        verify(clientRepository).findAll();
    }



    @Test
    void getById() {
        int clientId = 1;

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        when(clientMapper.toDTO(client)).thenReturn(clientDto);

        ClientDto foundClient = clientService.getById(clientId);

        assertNotNull(foundClient);
        assertEquals("John", foundClient.getNom());
        verify(clientRepository).findById(clientId);
    }

    @Test
    void getById_NotFound() {
        int clientId = 2;

        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());

        ClientDto foundClient = clientService.getById(clientId);

        assertNull(foundClient);
        verify(clientRepository).findById(clientId);
    }
}
