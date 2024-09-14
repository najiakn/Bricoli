package com.example.bricoli.Controller;

import com.example.bricoli.dto.ReclamationDto;
import com.example.bricoli.dto.ServiceDto;
import com.example.bricoli.service.ReclamantionService;
import com.example.bricoli.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/reclamations")
public class ReclamationController {

    @Autowired
    private ReclamantionService reclamantionService;

    @PostMapping("/create-reclamation")
    public ResponseEntity<?> createReclamation(@RequestBody ReclamationDto reclamationDto) {
        try {
            var reclamation = reclamantionService.create(reclamationDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(reclamation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReclamation(@PathVariable("id") int id) {
        reclamantionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ReclamationDto>> getAllServices() {
        List<ReclamationDto> reclamations = reclamantionService.getAll();
        return ResponseEntity.ok(reclamations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReclamationDto> getReclamationById(@PathVariable("id") int id) {
        ReclamationDto reclamation= reclamantionService.getRaclamationById(id);
        if (reclamation != null) {
            return ResponseEntity.ok(reclamation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReclamationDto> updateReclamation(@PathVariable("id") int id, @RequestBody ReclamationDto reclamationDto) {
        ReclamationDto updateReclamation = reclamantionService.update(id, reclamationDto);
        if (updateReclamation != null) {
            return ResponseEntity.ok(updateReclamation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
