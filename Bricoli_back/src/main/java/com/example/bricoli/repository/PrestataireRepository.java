package com.example.bricoli.repository;

import com.example.bricoli.models.Client;
import com.example.bricoli.models.Prestataire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestataireRepository   extends JpaRepository<Prestataire,Integer > {
}
