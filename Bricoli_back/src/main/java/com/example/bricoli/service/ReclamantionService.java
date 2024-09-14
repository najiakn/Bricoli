package com.example.bricoli.service;

import com.example.bricoli.dto.ReclamationDto;
import com.example.bricoli.dto.ServiceDto;

import java.util.List;

public interface ReclamantionService {
    ReclamationDto create(ReclamationDto reclamationDto);
    void delete(int id);
    List<ReclamationDto> getAll();
    ReclamationDto getRaclamationById(int id);
    ReclamationDto update(int id, ReclamationDto reclamationDto );
}
