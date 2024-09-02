package com.example.bricoli.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Prestataire extends Personne {

    
    private String zone_deplacement;

    @Lob
    private byte[] profil;

    @Lob
    private byte[] cnie;



    @ManyToMany
    @JoinTable(
            name = "prestataire_service", // Nom de la table de jointure
            joinColumns = @JoinColumn(name = "prestataire_id"), // Colonne de jointure pour Prestataire
            inverseJoinColumns = @JoinColumn(name = "service_id") // Colonne de jointure pour Service
    )
    private Set<ServiceModel> services;


}
