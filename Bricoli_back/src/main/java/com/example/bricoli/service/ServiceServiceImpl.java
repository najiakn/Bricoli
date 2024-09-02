package com.example.bricoli.service;

import com.example.bricoli.dto.ServiceDto;
import com.example.bricoli.mapper.ServiceMapper;
import com.example.bricoli.models.ServiceModel;
import com.example.bricoli.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceServiceImpl  implements ServiceService{
    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServiceMapper serviceMapper;


    @Override
    public ServiceDto create(ServiceDto serviceDto) {
        ServiceModel services = serviceMapper.toEntity(serviceDto);
        return serviceMapper.toDTO(serviceRepository.save(services));
    }


    @Override
    public void delete(int id) {
        serviceRepository.deleteById(id);
    }


    @Override
    public List<ServiceDto> getAll() {
        List<ServiceModel> services = serviceRepository.findAll();
        return services.stream()
                .map(serviceMapper::toDTO)
                .collect(Collectors.toList());
    }



    @Override()
    public ServiceDto getServiceById(int id) {
        Optional<ServiceModel> serviceModel = serviceRepository.findById(id);
        return serviceModel.map(serviceMapper::toDTO).orElse(null);
    }

    @Override
    public ServiceDto update(int id, ServiceDto serviceDto) {
        Optional<ServiceModel> optionalService = serviceRepository.findById(id);
        if (optionalService.isPresent()) {
            ServiceModel serviceModel = optionalService.get();
            serviceModel.setDescription(serviceModel.getDescription());
            serviceModel.setTypeService(serviceModel.getTypeService());
            serviceModel.setEtatService(serviceModel.getEtatService());
            serviceModel.setCategorie(serviceModel.getCategorie());
            serviceModel.setPrix(serviceModel.getPrix());
            serviceModel.setTitre(serviceModel.getTitre());
            serviceModel.setDate_Creation(serviceModel.getDate_Creation());
            serviceModel.setPrestataires(serviceModel.getPrestataires());
            serviceModel.setTypePaiement(serviceModel.getTypePaiement());
            serviceModel.setClients(serviceModel.getClients());

            ServiceModel updateService = serviceRepository.save(serviceModel);
            return serviceMapper.toDTO(updateService);
        } else {
            return null;
        }
    }


}
