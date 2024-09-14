package com.example.bricoli.service;

import com.example.bricoli.dto.ReclamationDto;
import com.example.bricoli.mapper.ReclamationMapper;
import com.example.bricoli.mapper.ServiceMapper;
import com.example.bricoli.models.Reclamation;
import com.example.bricoli.models.ServiceModel;
import com.example.bricoli.repository.ReclamationRepository;
import com.example.bricoli.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReclamationServiceImpl implements ReclamantionService {


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
            reclamation.setDescription(reclamation.getDescription());
            reclamation.setTitre(reclamation.getTitre());
            reclamation.setDate(reclamation.getDate());
            reclamation.setClient(reclamation.getClient());
            reclamation.setPrestataire(reclamation.getPrestataire());



            Reclamation updateReclamation = reclamationRepository.save(reclamation);
            return reclamationMapper.toDTO(updateReclamation);
        } else {
            return null;
        }


    }
    }

