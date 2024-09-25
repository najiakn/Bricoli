package com.example.bricoli.Controller;

import com.example.bricoli.dto.ServiceDto;
import com.example.bricoli.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin("*")

public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @PostMapping(value = "/create-service", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createService(@RequestPart("service") ServiceDto serviceDto, @RequestPart("image") MultipartFile image) {
        try {
            var service = serviceService.create(serviceDto,image);
            return ResponseEntity.status(HttpStatus.CREATED).body(service);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable("id") int id) {
        serviceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ServiceDto>> getAllServices() {
        List<ServiceDto> services = serviceService.getAll();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDto> getServiceById(@PathVariable("id") int id) {
        ServiceDto service = serviceService.getServiceById(id);
        if (service != null) {
            return ResponseEntity.ok(service);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceDto> updateService(@PathVariable("id") int id, @RequestBody ServiceDto serviceDto) {
        ServiceDto updateService = serviceService.update(id, serviceDto);
        if (updateService != null) {
            return ResponseEntity.ok(updateService);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
