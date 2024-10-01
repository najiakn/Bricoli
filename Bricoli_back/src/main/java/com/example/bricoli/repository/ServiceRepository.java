package com.example.bricoli.repository;

import com.example.bricoli.models.ServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRepository  extends JpaRepository<ServiceModel,Integer > {
    @Query(value = "SELECT * FROM service_model WHERE categorie = 'OFFRE'", nativeQuery = true)
    List<ServiceModel> findOffreServices();

    @Query(value = "SELECT * FROM service_model WHERE categorie = 'SERVICE'", nativeQuery = true)
    List<ServiceModel> findCategorieServices();
}
