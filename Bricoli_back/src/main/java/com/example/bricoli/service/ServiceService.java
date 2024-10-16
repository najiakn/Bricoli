package com.example.bricoli.service;

import com.example.bricoli.dto.ServiceDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ServiceService {
    public ServiceDto createServiceForPrestataire( ServiceDto serviceDto)  ;
    void delete(int id);
    public List<ServiceDto> getServicesByAuthenticatedClient();
    public List<ServiceDto> getServicesByAuthenticatedPrestataire();
    List<ServiceDto> getAll();
    ServiceDto getServiceById(int id);
    ServiceDto update(int id, ServiceDto serviceDto );
    List<ServiceDto> getAllOffreServices();
    List<ServiceDto> getAllCategorieService();
    List<ServiceDto>getAllOffres();
     List<ServiceDto> getServicesByClient(int clientId);
     ServiceDto createServiceForClient(ServiceDto serviceDto);
}
