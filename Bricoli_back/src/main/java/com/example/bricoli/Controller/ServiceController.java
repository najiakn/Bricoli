package com.example.bricoli.Controller;

import com.example.bricoli.dto.ServiceDto;
import com.example.bricoli.models.Personne;
import com.example.bricoli.service.ServiceService;
import com.example.bricoli.service.TypeServieService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin("*")

public class ServiceController {

    @Autowired
    private ServiceService serviceService;


    @Autowired
    private TypeServieService typeServieService;

    @PostMapping( "/create-service")
    public ResponseEntity<?> createService(@RequestBody ServiceDto serviceDto) {
        try {
            var service = serviceService.create(serviceDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(service);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-service/{id}")
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

    @PutMapping("/update-service/{id}")
    public ResponseEntity<ServiceDto> updateService(@PathVariable("id") int id, @RequestBody ServiceDto serviceDto) {
        ServiceDto updateService = serviceService.update(id, serviceDto);
        if (updateService != null) {
            return ResponseEntity.ok(updateService);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/offre")
    public ResponseEntity<List<ServiceDto>> getAllOffreServices() {
        List<ServiceDto> offreServices = serviceService.getAllOffreServices();
        return ResponseEntity.ok(offreServices);
    }



    @GetMapping("/categoriService")
    public ResponseEntity<List<ServiceDto>> getAllCategoriServices() {
        List<ServiceDto> categoriServices = serviceService.getAllCategorieService();
        return ResponseEntity.ok(categoriServices);
    }




}
