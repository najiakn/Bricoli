package com.example.bricoli.repository;

import com.example.bricoli.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer > {
}
