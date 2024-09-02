package com.example.bricoli.repository;

import com.example.bricoli.models.ServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository  extends JpaRepository<ServiceModel,Integer > {
}
