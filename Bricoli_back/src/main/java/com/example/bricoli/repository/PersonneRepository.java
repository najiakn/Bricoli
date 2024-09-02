package com.example.bricoli.repository;

import com.example.bricoli.models.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonneRepository  extends JpaRepository<Personne,Integer > {

}
