package com.example.bricoli.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Client  extends Personne{


    @ManyToMany
    @JoinTable(
            name = "client_service", // Nom de la table de jointure
            joinColumns = @JoinColumn(name = "clientId"), // Colonne de jointure pour Prestataire
            inverseJoinColumns = @JoinColumn(name = "serviceId") // Colonne de jointure pour Service
    )
    private Set<ServiceModel> services;
}
