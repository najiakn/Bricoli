package com.example.bricoli.Controller;

import com.example.bricoli.dto.ClientDto;
import com.example.bricoli.dto.PrestataireDto;
import com.example.bricoli.models.Client;
import com.example.bricoli.models.Prestataire;
import com.example.bricoli.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/create-client")
    public ResponseEntity<?> createClient(@RequestBody ClientDto clientDto) {
        try {
            var client = clientService.create(clientDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(client);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") int id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> getAllClients() {
        List<ClientDto> clientDtos = clientService.getAll();
        return ResponseEntity.ok(clientDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable("id") int id) {
        ClientDto clientDtos= clientService.getById(id);
        if (clientDtos != null) {
            return ResponseEntity.ok(clientDtos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> updateClient
            (@PathVariable("id") int id, @RequestBody ClientDto clientDto) {
        ClientDto updateClient = clientService.update(id, clientDto);
        if (updateClient != null) {
            return ResponseEntity.ok(updateClient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<ClientDto> getAuthenticateClient() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client authenticationPrincipal = (Client) authentication.getPrincipal(); // Récupérer le prestataire connecté

        ClientDto clientDto = clientService.getById(authenticationPrincipal.getId());

        if (clientDto != null) {
            return ResponseEntity.ok(clientDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}





