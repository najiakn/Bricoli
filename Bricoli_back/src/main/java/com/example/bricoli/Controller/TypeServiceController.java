package com.example.bricoli.Controller;

import com.example.bricoli.dto.TypeServiceDto;
import com.example.bricoli.service.TypeServieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/typeServices")
public class TypeServiceController {

    @Autowired
    private TypeServieService typeServieService;

    @PostMapping("/create-type-service")
    public ResponseEntity<?> createTypeService(@RequestBody TypeServiceDto typeServiceDto) {
        try {
            var typeService = typeServieService.create(typeServiceDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(typeService);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-type-service/{id}")
    public ResponseEntity<Void> deleteTypeService(@PathVariable("id") int id) {
        typeServieService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TypeServiceDto>> getAllTypeService() {
        List<TypeServiceDto> typeServiceDtos = typeServieService.getAll();
        return ResponseEntity.ok(typeServiceDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeServiceDto> getTypeById(@PathVariable("id") int id) {
        TypeServiceDto typeServiceDto= typeServieService.getTypeServiceById(id);
        if (typeServiceDto != null) {
            return ResponseEntity.ok(typeServiceDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update-type-service/{id}")
    public ResponseEntity<TypeServiceDto> updateTypeService
            (@PathVariable("id") int id, @RequestBody TypeServiceDto typeServiceDto) {
        TypeServiceDto updateTypeService = typeServieService.update(id, typeServiceDto);
        if (updateTypeService != null) {
            return ResponseEntity.ok(updateTypeService);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
