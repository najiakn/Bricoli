package com.example.bricoli.service;

import com.example.bricoli.dto.ClientDto;
import com.example.bricoli.dto.PrestataireDto;
import com.example.bricoli.dto.TypeServiceDto;
import com.example.bricoli.mapper.TypeServiceMapper;
import com.example.bricoli.models.Client;
import com.example.bricoli.models.Prestataire;
import com.example.bricoli.models.TypeService;
import com.example.bricoli.repository.TypeServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TypeServiceImpl  implements  TypeServieService {
    @Autowired
    private TypeServiceMapper typeServiceMapper;

    @Autowired
    private TypeServiceRepository typeServiceRepository;


    @Override
    public TypeServiceDto create(TypeServiceDto typeServiceDto) {
        var typeservice = typeServiceMapper.toEntity(typeServiceDto);
        return typeServiceMapper.toDTO(typeServiceRepository.save(typeservice));
    }


    @Override
    public void delete(int id) {
        typeServiceRepository.deleteById(id);

    }

    @Override
    public List<TypeServiceDto> getAll() {
        List<TypeService> typeServiceList = typeServiceRepository.findAll();
        return typeServiceList.stream()
                .map(typeServiceMapper::toDTO)
                .collect(Collectors.toList());

    }


    @Override
    public TypeServiceDto getTypeServiceById(int id) {
        Optional<TypeService> typeService = typeServiceRepository.findById(id);
        return typeService.map(typeServiceMapper::toDTO).orElse(null);
    }

    @Override
    public TypeServiceDto update(int id, TypeServiceDto typeServiceDto) {
        Optional<TypeService> optionalTypeService = typeServiceRepository.findById(id);
        if (optionalTypeService.isPresent()) {
            TypeService typeService = optionalTypeService.get();
            typeService.setNomType(typeServiceDto.getNomType());

            TypeService updateTypeService =typeServiceRepository.save(typeService);
            return typeServiceMapper.toDTO(updateTypeService);
        } else {
            return null;
        }
    }
    

 }










