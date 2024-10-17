package com.example.bricoli.service;

import com.example.bricoli.dto.PrestataireDto;
import com.example.bricoli.mapper.PrestataireMapper;
import com.example.bricoli.models.Prestataire;
import com.example.bricoli.repository.PrestataireRepository;
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

class PrestataireServiceImplTest {

    @Mock
    private PrestataireRepository prestataireRepository;

    @Mock
    private PrestataireMapper prestataireMapper;

    @InjectMocks
    private PrestataireServiceImpl prestataireService;

    private Prestataire prestataire;
    private PrestataireDto prestataireDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        prestataire = new Prestataire();
        prestataire.setId(1);
        prestataire.setNom("John");

        prestataireDto = new PrestataireDto();
        prestataireDto.setId(1);
        prestataireDto.setNom("John");
    }

    @Test
    void create() {
        when(prestataireMapper.toEntity(any(PrestataireDto.class))).thenReturn(prestataire);
        when(prestataireRepository.save(any(Prestataire.class))).thenReturn(prestataire);
        when(prestataireMapper.toDTO(any(Prestataire.class))).thenReturn(prestataireDto);

        PrestataireDto result = prestataireService.create(prestataireDto);

        assertNotNull(result);
        assertEquals(prestataireDto.getNom(), result.getNom());
        verify(prestataireRepository, times(1)).save(any(Prestataire.class));
    }

    @Test
    void delete() {
        doNothing().when(prestataireRepository).deleteById(anyInt());

        prestataireService.delete(1);

        verify(prestataireRepository, times(1)).deleteById(1);
    }

    @Test
    void getAll() {
        List<Prestataire> prestataireList = Arrays.asList(prestataire);
        List<PrestataireDto> prestataireDtoList = Arrays.asList(prestataireDto);

        when(prestataireRepository.findAll()).thenReturn(prestataireList);
        when(prestataireMapper.toDTO(any(Prestataire.class))).thenReturn(prestataireDto);

        List<PrestataireDto> result = prestataireService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(prestataireRepository, times(1)).findAll();
    }

    @Test
    void update() {
        when(prestataireRepository.findById(anyInt())).thenReturn(Optional.of(prestataire));
        when(prestataireRepository.save(any(Prestataire.class))).thenReturn(prestataire);
        when(prestataireMapper.toDTO(any(Prestataire.class))).thenReturn(prestataireDto);

        PrestataireDto result = prestataireService.update(1, prestataireDto);

        assertNotNull(result);
        assertEquals(prestataireDto.getNom(), result.getNom());
        verify(prestataireRepository, times(1)).save(any(Prestataire.class));
    }

    @Test
    void getById() {
        when(prestataireRepository.findById(anyInt())).thenReturn(Optional.of(prestataire));
        when(prestataireMapper.toDTO(any(Prestataire.class))).thenReturn(prestataireDto);

        PrestataireDto result = prestataireService.getById(1);

        assertNotNull(result);
        assertEquals(prestataireDto.getNom(), result.getNom());
        verify(prestataireRepository, times(1)).findById(1);
    }
}
