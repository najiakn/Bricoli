package com.example.bricoli.service;

import com.example.bricoli.dto.PersonneDto;
import com.example.bricoli.dto.PrestataireDto;
import com.example.bricoli.models.Personne;

import java.util.List;

public interface PersonneService {
    PersonneDto create (PersonneDto personneDto);
    PersonneDto update (int id, PersonneDto personneDto);
    void delete (int id);
    List<PersonneDto> getAll();
    PersonneDto getById(int id);
}
