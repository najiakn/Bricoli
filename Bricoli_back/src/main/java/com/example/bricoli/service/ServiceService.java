package com.example.bricoli.service;

import com.example.bricoli.dto.ServiceDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ServiceService {
    ServiceDto create(ServiceDto serviceDto) ;
    void delete(int id);
    List<ServiceDto> getAll();
    ServiceDto getServiceById(int id);
    ServiceDto update(int id, ServiceDto serviceDto );
    List<ServiceDto> getAllOffreServices();
    List<ServiceDto> getAllCategorieService();
    List<ServiceDto>getAllOffres();

}
