package com.example.bricoli.service;

import com.example.bricoli.dto.TypeServiceDto;

import java.util.List;

public interface TypeServieService {
    TypeServiceDto create(TypeServiceDto typeServiceDto);
    void delete(int id);
    List<TypeServiceDto> getAll();
    TypeServiceDto getTypeServiceById(int id);
    TypeServiceDto update(int id, TypeServiceDto typeServiceDto );
    String findNameById(Integer id);
}
