package com.example.bricoli.repository;

import com.example.bricoli.models.Client;
import com.example.bricoli.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository   extends JpaRepository<Service,Integer > {
}
