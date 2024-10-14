package com.example.bricoli.service;

import com.example.bricoli.dto.PersonneDto;
import com.example.bricoli.dto.PrestataireDto;
import com.example.bricoli.mapper.PersonneMapper;
import com.example.bricoli.mapper.PrestataireMapper;
import com.example.bricoli.models.Personne;
import com.example.bricoli.models.Prestataire;
import com.example.bricoli.repository.PersonneRepository;
import com.example.bricoli.repository.PrestataireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class PersonneServiceImpl implements PersonneService{
    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    private PersonneMapper personneMapper;

    @Override
    public PersonneDto create(PersonneDto personneDto){
        var personne = personneMapper.toEntity(personneDto);
        return personneMapper.toDTO(personneRepository.save(personne) );
    }


    @Override
    public void delete(int id){
        personneRepository.deleteById(id);
    }
    @Override
    public List<PersonneDto> getAll(){
        List<Personne> personneList=personneRepository.findAll();
        return  personneList.stream()
                .map(personneMapper::toDTO)
                .collect(Collectors.toList());


    }
    @Override
    public PersonneDto update (int id , PersonneDto personneDto){
        Optional<Personne> optionalPersonne= personneRepository.findById(id);
        if(optionalPersonne.isPresent()){
            Personne personne = optionalPersonne.get();
            personne.setNom(personneDto.getNom());
            personne.setPrenom(personneDto.getPrenom());
            personne.setEmail(personneDto.getEmail());
            personne.setCnie(personneDto.getCnie());
            personne.setZoneDeplacement(personneDto.getZoneDeplacement());
            personne.setAge(personneDto.getAge());
            personne.setGenre(personneDto.getGenre());
            personne.setPassword(personneDto.getPassword());
            personne.setVille(personneDto.getVille());
            personne.setTelephone(personneDto.getTelephone());
            Personne updatePersonne =personneRepository.save(personne);
            return personneMapper.toDTO(updatePersonne);


        }
        else {
            return  null;
        }

    }
    public PersonneDto getById(int id){
        Optional<Personne> personne=personneRepository.findById(id);
        return personne.map(personneMapper::toDTO).orElse(null);
    }
}
