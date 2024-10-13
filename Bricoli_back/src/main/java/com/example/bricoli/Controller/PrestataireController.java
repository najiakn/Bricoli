package com.example.bricoli.Controller;

import com.example.bricoli.dto.PrestataireDto;
import com.example.bricoli.dto.ReclamationDto;
import com.example.bricoli.models.Prestataire;
import com.example.bricoli.service.PrestataireService;
import com.example.bricoli.service.ReclamantionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestataires")
public class PrestataireController {
    @Autowired
    private PrestataireService prestataireService;

    @PostMapping("/create-prestataire")
    public ResponseEntity<?> createPrestataire(@RequestBody PrestataireDto prestataireDto) {
        try {
            var prestataire = prestataireService.create(prestataireDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(prestataire);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrestataire(@PathVariable("id") int id) {
        prestataireService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PrestataireDto>> getAllPrestataires() {
        List<PrestataireDto> prestataires = prestataireService.getAll();
        return ResponseEntity.ok(prestataires);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestataireDto> getPrestataireById(@PathVariable("id") int id) {
        PrestataireDto prestataires= prestataireService.getById(id);
        if (prestataires != null) {
            return ResponseEntity.ok(prestataires);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrestataireDto> updatePrestataire
            (@PathVariable("id") int id, @RequestBody PrestataireDto prestataireDto) {
        PrestataireDto updatePrestataire = prestataireService.update(id, prestataireDto);
        if (updatePrestataire != null) {
            return ResponseEntity.ok(updatePrestataire);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/me/{id}")
    public ResponseEntity<PrestataireDto> updateOwnAccount(@PathVariable("id") int id, @RequestBody PrestataireDto prestataireDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Prestataire prestataireAuthentifie = (Prestataire) authentication.getPrincipal(); // Récupérer l'utilisateur connecté

        if (prestataireAuthentifie.getId() == id) {
            PrestataireDto updatedPrestataire = prestataireService.update(id, prestataireDto);
            return ResponseEntity.ok(updatedPrestataire);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<PrestataireDto> getAuthenticatedPrestataire() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Prestataire prestataireAuthentifie = (Prestataire) authentication.getPrincipal(); // Récupérer le prestataire connecté

        PrestataireDto prestataireDto = prestataireService.getById(prestataireAuthentifie.getId());

        if (prestataireDto != null) {
            return ResponseEntity.ok(prestataireDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
