package com.example.bricoli.service;

import com.example.bricoli.dto.ServiceDto;
import com.example.bricoli.enums.Etat_service;
import com.example.bricoli.mapper.ReclamationMapper;
import com.example.bricoli.mapper.ServiceMapper;
import com.example.bricoli.models.*;
import com.example.bricoli.repository.ClientRepository;
import com.example.bricoli.repository.PrestataireRepository;
import com.example.bricoli.repository.ServiceRepository;
import com.example.bricoli.repository.TypeServiceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServiceServiceImpl  implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private TypeServiceRepository typeServiceRepository;
    @Autowired
    private PrestataireRepository prestataireRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ReclamationMapper reclamationMapper;

    @Autowired
    private ServiceMapper serviceMapper;

    @Autowired
    private CloudinaryService cloudinaryService;


    public ServiceDto createServiceForPrestataire(ServiceDto serviceDto) {
        // Récupérer l'authentification actuelle
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Extraire l'objet Personne à partir des détails de l'utilisateur authentifié
        Personne personne = (Personne) authentication.getPrincipal();

        // Vérifier si l'utilisateur est un prestataire
        Prestataire prestataire = prestataireRepository.findById(personne.getId())
                .orElseThrow(() -> new EntityNotFoundException("Prestataire not found"));

        // Mapper le DTO vers l'entité ServiceModel
        ServiceModel serviceModel = serviceMapper.toEntity(serviceDto);

        // Sauvegarder le service dans le repository
        ServiceModel savedService = serviceRepository.save(serviceModel);

        // Ajouter le service au prestataire via la table Many-to-Many
        prestataire.getServices().add(savedService);

        // Sauvegarder la mise à jour du prestataire avec les services associés
        prestataireRepository.save(prestataire);

        // Retourner le service sauvegardé en tant que DTO
        return serviceMapper.toDTO(savedService);
    }


    public ServiceDto createServiceForClient(ServiceDto serviceDto) {
        // Récupérer l'authentification actuelle
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Extraire l'objet Personne à partir des détails de l'utilisateur authentifié
        Personne personne = (Personne) authentication.getPrincipal();

        // Vérifier si l'utilisateur est un prestataire
        Client client = clientRepository.findById(personne.getId())
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        // Mapper le DTO vers l'entité ServiceModel
        ServiceModel serviceModel = serviceMapper.toEntity(serviceDto);

        // Sauvegarder le service dans le repository
        ServiceModel savedService = serviceRepository.save(serviceModel);

        // Ajouter le service au prestataire via la table Many-to-Many
        client.getServices().add(savedService);

        // Sauvegarder la mise à jour du prestataire avec les services associés
        clientRepository.save(client);

        // Retourner le service sauvegardé en tant que DTO
        return serviceMapper.toDTO(savedService);
    }

@Override
    public List<ServiceDto> getServicesByAuthenticatedPrestataire() {
        // Récupérer l'authentification actuelle
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Extraire l'objet Personne à partir des détails de l'utilisateur authentifié
        Personne personne = (Personne) authentication.getPrincipal();

        // Vérifier si l'utilisateur est un prestataire
        Prestataire prestataire = prestataireRepository.findById(personne.getId())
                .orElseThrow(() -> new EntityNotFoundException("Prestataire not found"));

        // Récupérer les services associés à ce prestataire
        Set<ServiceModel> services = prestataire.getServices();

        // Mapper les entités ServiceModel vers des DTO
        return services.stream()
                .map(serviceMapper::toDTO)
                .collect(Collectors.toList());
    }



    @Override
    public List<ServiceDto> getServicesByAuthenticatedClient() {
        // Récupérer l'authentification actuelle
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Extraire l'objet Personne à partir des détails de l'utilisateur authentifié
        Personne personne = (Personne) authentication.getPrincipal();

        // Vérifier si l'utilisateur est un prestataire
        Client client = clientRepository.findById(personne.getId())
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        // Récupérer les services associés à ce prestataire
        Set<ServiceModel> services = client.getServices();

        // Mapper les entités ServiceModel vers des DTO
        return services.stream()
                .map(serviceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(int id) {
        serviceRepository.deleteById(id);
    }


    @Override
    public List<ServiceDto> getAll() {
        List<ServiceModel> servicemodels = serviceRepository.findAll();
        return servicemodels.stream()
                .map(serviceMapper::toDTO)
                .collect(Collectors.toList());
    }




    public List<ServiceDto> getAllOffreServices() {
        List<ServiceModel> serviceModels = serviceRepository.findOffreServices();
        return serviceModels.stream()
                .map(serviceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceDto> getAllCategorieService() {
        List<ServiceModel> serviceModels = serviceRepository.findCategorieServices();
        return serviceModels.stream()
                .map(serviceMapper::toDTO)
                .collect(Collectors.toList());    }

    @Override
    public List<ServiceDto> getAllOffres() {
        List<ServiceModel> serviceModels = serviceRepository.findOffres();
        return serviceModels.stream()
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
            serviceModel.setDescription(serviceDto.getDescription());
            serviceModel.setEtatService(serviceDto.getEtatService());
            serviceModel.setCategorie(serviceDto.getCategorie());
            serviceModel.setPrix(serviceDto.getPrix());
            serviceModel.setTitre(serviceDto.getTitre());
            serviceModel.setDateCreation(serviceDto.getDateCreation());
            serviceModel.setTypePaiement(serviceDto.getTypePaiement());
            serviceModel.setImageUrl(serviceDto.getImageUrl());

            TypeService typeService = typeServiceRepository.findById(serviceDto.getIdType())
                    .orElseThrow(() -> new EntityNotFoundException("TypeService not found"));
            serviceModel.setTypeService(typeService);

            // Save updated serviceModel
            serviceRepository.save(serviceModel);




            ServiceModel updateService = serviceRepository.save(serviceModel);
            return serviceMapper.toDTO(updateService);
        } else {
            return null;
        }


    }
    @Override
    public List<ServiceDto> getServicesByClient(int clientId) {
        List<ServiceModel> serviceModels = serviceRepository.findByClients_Id(clientId);
        return serviceModels.stream()
                .map(serviceMapper::toDTO)
                .collect(Collectors.toList());
    }

}