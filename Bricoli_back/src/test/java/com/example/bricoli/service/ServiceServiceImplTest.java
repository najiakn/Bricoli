package com.example.bricoli.service;

import com.example.bricoli.dto.ServiceDto;
import com.example.bricoli.mapper.ServiceMapper;
import com.example.bricoli.models.Client;
import com.example.bricoli.models.Prestataire;
import com.example.bricoli.models.ServiceModel;
import com.example.bricoli.repository.ClientRepository;
import com.example.bricoli.repository.PrestataireRepository;
import com.example.bricoli.repository.ServiceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

import static org.mockito.Mockito.*;

class ServiceServiceImplTest {

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private PrestataireRepository prestataireRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ServiceMapper serviceMapper;

    @InjectMocks
    private ServiceServiceImpl serviceService;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void delete() {
        int serviceId = 1;

        doNothing().when(serviceRepository).deleteById(serviceId);

        serviceService.delete(serviceId);

        verify(serviceRepository, times(1)).deleteById(serviceId);
    }


}
