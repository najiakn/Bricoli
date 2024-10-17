package com.example.bricoli.service;

import com.example.bricoli.dto.ReclamationDto;
import com.example.bricoli.mapper.ReclamationMapper;
import com.example.bricoli.models.Client;
import com.example.bricoli.models.Prestataire;
import com.example.bricoli.models.Reclamation;
import com.example.bricoli.repository.ClientRepository;
import com.example.bricoli.repository.PrestataireRepository;
import com.example.bricoli.repository.ReclamationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class ReclamationServiceImplTest {

    @Mock
    private ReclamationRepository reclamationRepository;

    @Mock
    private ReclamationMapper reclamationMapper;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private PrestataireRepository prestataireRepository;

    @InjectMocks
    private ReclamationServiceImpl reclamationService;

    private Reclamation reclamation;
    private ReclamationDto reclamationDto;
    private Client client;
    private Prestataire prestataire;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reclamation = new Reclamation();
        reclamation.setId(1);
        reclamation.setDescription("Problème de service");

        reclamationDto = new ReclamationDto();
        reclamationDto.setId(1);
        reclamationDto.setDescription("Problème de service");

        client = new Client();
        client.setId(1);

        prestataire = new Prestataire();
        prestataire.setId(1);
    }

    @Test
    void create() {
        when(reclamationMapper.toEntity(any(ReclamationDto.class))).thenReturn(reclamation);
        when(reclamationRepository.save(any(Reclamation.class))).thenReturn(reclamation);
        when(reclamationMapper.toDTO(any(Reclamation.class))).thenReturn(reclamationDto);

        ReclamationDto result = reclamationService.create(reclamationDto);

        assertNotNull(result);
        assertEquals(reclamationDto.getDescription(), result.getDescription());
        verify(reclamationRepository, times(1)).save(any(Reclamation.class));
    }

    @Test
    void delete() {
        doNothing().when(reclamationRepository).deleteById(anyInt());

        reclamationService.delete(1);

        verify(reclamationRepository, times(1)).deleteById(1);
    }

    @Test
    void getAll() {
        List<Reclamation> reclamations = Arrays.asList(reclamation);
        List<ReclamationDto> reclamationDtos = Arrays.asList(reclamationDto);

        when(reclamationRepository.findAll()).thenReturn(reclamations);
        when(reclamationMapper.toDTO(any(Reclamation.class))).thenReturn(reclamationDto);

        List<ReclamationDto> result = reclamationService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(reclamationRepository, times(1)).findAll();
    }

    @Test
    void getRaclamationById() {
        when(reclamationRepository.findById(anyInt())).thenReturn(Optional.of(reclamation));
        when(reclamationMapper.toDTO(any(Reclamation.class))).thenReturn(reclamationDto);

        ReclamationDto result = reclamationService.getRaclamationById(1);

        assertNotNull(result);
        assertEquals(reclamationDto.getDescription(), result.getDescription());
        verify(reclamationRepository, times(1)).findById(1);
    }

    @Test
    void update() {
        when(reclamationRepository.findById(anyInt())).thenReturn(Optional.of(reclamation));
        when(clientRepository.findById(anyInt())).thenReturn(Optional.of(client));
        when(prestataireRepository.findById(anyInt())).thenReturn(Optional.of(prestataire));
        when(reclamationRepository.save(any(Reclamation.class))).thenReturn(reclamation);
        when(reclamationMapper.toDTO(any(Reclamation.class))).thenReturn(reclamationDto);

        ReclamationDto result = reclamationService.update(1, reclamationDto);

        assertNotNull(result);
        assertEquals(reclamationDto.getDescription(), result.getDescription());
        verify(reclamationRepository, times(1)).save(any(Reclamation.class));
    }




}
