package com.example.bricoli.service;

import com.example.bricoli.dto.ClientDto;
import com.example.bricoli.dto.PrestataireDto;
import com.example.bricoli.mapper.ClientMapper;
import com.example.bricoli.mapper.PrestataireMapper;
import com.example.bricoli.models.Client;
import com.example.bricoli.models.Prestataire;
import com.example.bricoli.repository.ClientRepository;
import com.example.bricoli.repository.PrestataireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ClinetServiceImpl implements ClientService{
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public ClientDto create(ClientDto clientDto){
        var client = clientMapper.toEntity(clientDto);
        return clientMapper.toDTO(clientRepository.save(client) );
    }


    @Override
    public void delete(int id){
        clientRepository.deleteById(id);
    }
    @Override
    public List<ClientDto> getAll(){
        List<Client> clientList=clientRepository.findAll();
        return  clientList.stream()
                .map(clientMapper::toDTO)
                .collect(Collectors.toList());


    }
    @Override
    public ClientDto update (int id , ClientDto clientDto){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if(optionalClient.isPresent()){
            Client client = optionalClient.get();
            client.setNom(clientDto.getNom());
            client.setPrenom(clientDto.getPrenom());
            client.setAge(clientDto.getAge());
            client.setGenre(clientDto.getGenre());
            client.setTelephone(clientDto.getTelephone());
            client.setVille(clientDto.getVille());
            client.setCnie(clientDto.getCnie());

            Client updateClient =clientRepository.save(client);
            return clientMapper.toDTO(updateClient);


        }
        else {
            return  null;
        }

    }
    public ClientDto getById(int id){
        Optional<Client> client=clientRepository.findById(id);
        return client.map(clientMapper::toDTO).orElse(null);
    }
}
