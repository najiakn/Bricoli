package com.example.bricoli.service;

import com.example.bricoli.dto.PrestataireDto;

import java.util.List;

public interface PrestataireService {
    PrestataireDto create (PrestataireDto prestataireDto);
    PrestataireDto update (int id, PrestataireDto prestataireDto);
    void delete (int id);
   List<PrestataireDto> getAll();
    PrestataireDto getById(int id);
}
