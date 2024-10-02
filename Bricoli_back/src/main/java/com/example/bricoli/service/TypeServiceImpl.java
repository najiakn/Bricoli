package com.example.bricoli.service;


import com.example.bricoli.dto.TypeServiceDto;
import com.example.bricoli.exceptions.TypeServiceAlreadyExistsException;
import com.example.bricoli.mapper.TypeServiceMapper;

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


    public TypeServiceDto create(TypeServiceDto typeServiceDto) {
        // Check if a TypeService with the same nomType already exists
        if (typeServiceRepository.existsByNomType(typeServiceDto.getNomType())) {
            throw new TypeServiceAlreadyExistsException("Le type de service '" + typeServiceDto.getNomType() + "' existe déjà.");
        }

        var typeService = typeServiceMapper.toEntity(typeServiceDto);
        var savedTypeService = typeServiceRepository.save(typeService);
        return typeServiceMapper.toDTO(savedTypeService);

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

            TypeService updateTypeService = typeServiceRepository.save(typeService);
            return typeServiceMapper.toDTO(updateTypeService);
        } else {
            return null;
        }
    }


    public String findNameById(Integer id) {
        return typeServiceRepository.findNameById(id);
    }


}









