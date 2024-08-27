package com.example.bricoli.models;


import com.example.bricoli.enums.Type_service;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Prestataire extends Personne {

    @Lob
    private String zone_deplacement;

    @Lob
    private byte[] profil;
    @Lob
    private byte[] cnie;

    @Enumerated
    private Type_service type_service;

}
