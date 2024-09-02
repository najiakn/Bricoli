package com.example.bricoli.repository;

import com.example.bricoli.models.Client;
import com.example.bricoli.models.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReclamationRepository   extends JpaRepository<Reclamation,Integer > {
}
