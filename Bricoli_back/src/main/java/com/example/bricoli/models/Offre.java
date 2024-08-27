package com.example.bricoli.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Offre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    @Lob
    private String description;
    private  int prix ;
    private  String adresse;
    private Date date_travail;
    @ManyToOne
    @JoinColumn(name = "id_client",nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_pres",nullable = false)
    private Prestataire prestataire;
}
