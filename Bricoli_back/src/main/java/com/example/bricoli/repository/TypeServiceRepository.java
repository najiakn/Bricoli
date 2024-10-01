package com.example.bricoli.repository;

import com.example.bricoli.models.ServiceModel;
import com.example.bricoli.models.TypeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TypeServiceRepository extends JpaRepository<TypeService,Integer > {
    @Query("SELECT t.nomType FROM TypeService t WHERE t.id = :id")
    String findNameById(Integer id);
}
