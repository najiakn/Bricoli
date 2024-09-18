package com.example.bricoli.service;

import com.example.bricoli.dto.ReclamationDto;
import com.example.bricoli.mapper.ReclamationMapper;
import com.example.bricoli.mapper.ServiceMapper;
import com.example.bricoli.models.Client;
import com.example.bricoli.models.Prestataire;
import com.example.bricoli.models.Reclamation;
import com.example.bricoli.models.ServiceModel;
import com.example.bricoli.repository.ClientRepository;
import com.example.bricoli.repository.PrestataireRepository;
import com.example.bricoli.repository.ReclamationRepository;
import com.example.bricoli.repository.ServiceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReclamationServiceImpl implements ReclamantionService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PrestataireRepository prestataireRepository;

    @Autowired
    private ReclamationRepository reclamationRepository;
    @Autowired
    private ReclamationMapper reclamationMapper;



    @Override
    public ReclamationDto create(ReclamationDto reclamationDto) {
        Reclamation reclamation = reclamationMapper.toEntity(reclamationDto);
        return reclamationMapper.toDTO(reclamationRepository.save(reclamation));
    }

    @Override
    public void delete(int id) {
        reclamationRepository.deleteById(id);
    }

    @Override
    public List<ReclamationDto> getAll() {
        List<Reclamation> reclamations = reclamationRepository.findAll();
        return reclamations.stream()
                .map(reclamationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReclamationDto getRaclamationById(int id) {
        Optional<Reclamation> reclamation = reclamationRepository.findById(id);
        return reclamation.map(reclamationMapper::toDTO).orElse(null);
    }

    @Override
    public ReclamationDto update(int id, ReclamationDto reclamationDto) {
        Optional<Reclamation> optionalReclamation= reclamationRepository.findById(id);
        if (optionalReclamation.isPresent()) {
            Reclamation reclamation = optionalReclamation.get();
            reclamation.setDescription(reclamationDto.getDescription());
            reclamation.setTitre(reclamationDto.getTitre());
            reclamation.setDate(reclamationDto.getDate());
            // Récupération des entités Client et Prestataire à partir des IDs
            Optional<Client> clientOptional = clientRepository.findById(reclamationDto.getClientId());
            Optional<Prestataire> prestataireOptional = prestataireRepository.findById(reclamationDto.getPrestataireId());

            // Assigner les objets client et prestataire à la réclamation si disponibles
            if (clientOptional.isPresent()) {
                reclamation.setClient(clientOptional.get());
            } else {
                // Gérer l'erreur si le client n'existe pas
                throw new EntityNotFoundException("Client with ID " + reclamationDto.getClientId() + " not found.");
            }

            if (prestataireOptional.isPresent()) {
                reclamation.setPrestataire(prestataireOptional.get());
            } else {
                // Gérer l'erreur si le prestataire n'existe pas
                throw new EntityNotFoundException("Prestataire with ID " + reclamationDto.getPrestataireId() + " not found.");
            }


            Reclamation updateReclamation = reclamationRepository.save(reclamation);
            return reclamationMapper.toDTO(updateReclamation);
        } else {
            return null;
        }


    }
    }

