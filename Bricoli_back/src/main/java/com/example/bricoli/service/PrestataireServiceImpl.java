package com.example.bricoli.service;

import com.example.bricoli.dto.PrestataireDto;
import com.example.bricoli.dto.ReclamationDto;
import com.example.bricoli.mapper.PrestataireMapper;
import com.example.bricoli.models.Prestataire;
import com.example.bricoli.repository.PrestataireRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrestataireServiceImpl implements PrestataireService{

    @Autowired
    private PrestataireRepository prestataireRepository;

    @Autowired
    private PrestataireMapper prestataireMapper;

    @Override
    public PrestataireDto create(PrestataireDto prestataireDto){
        var prestataire = prestataireMapper.toEntity(prestataireDto);
        return prestataireMapper.toDTO(prestataireRepository.save(prestataire) );
    }


    @Override
    public void delete(int id){
         prestataireRepository.deleteById(id);
    }
@Override
    public List<PrestataireDto> getAll(){
        List<Prestataire> prestataireList=prestataireRepository.findAll();
        return  prestataireList.stream()
                .map(prestataireMapper::toDTO)
                .collect(Collectors.toList());


    }
@Override
    public PrestataireDto update (int id , PrestataireDto prestataireDto){
        Optional<Prestataire> optionalPrestataire = prestataireRepository.findById(id);
        if(optionalPrestataire.isPresent()){
            Prestataire prestataire = optionalPrestataire.get();
            prestataire.setNom(prestataireDto.getNom());
            prestataire.setPrenom(prestataireDto.getPrenom());
            prestataire.setCnie(prestataireDto.getCnie());
            prestataire.setZoneDeplacement(prestataireDto.getZoneDeplacement());
            prestataire.setAge(prestataireDto.getAge());
            prestataire.setGenre(prestataireDto.getGenre());
            prestataire.setVille(prestataireDto.getVille());
            prestataireDto.setTelephone(prestataireDto.getTelephone());
           Prestataire updatePrestataire =prestataireRepository.save(prestataire);
           return prestataireMapper.toDTO(updatePrestataire);


        }
        else {
            return  null;
        }

    }
    public PrestataireDto getById(int id){
        Optional<Prestataire> prestataire=prestataireRepository.findById(id);
        return prestataire.map(prestataireMapper::toDTO).orElse(null);
    }
}
