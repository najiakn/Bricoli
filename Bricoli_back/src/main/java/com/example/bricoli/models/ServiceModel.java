package com.example.bricoli.models;

import com.example.bricoli.enums.Categorie;
import com.example.bricoli.enums.Etat_service;
import com.example.bricoli.enums.TypePaiement;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titre;
    private String description;
    private  int prix ;
    private Date dateCreation;

    private String imageUrl;
    private String cloudinaryImageId;
    @ManyToMany(mappedBy = "services")
    private Set<Prestataire> prestataires = new HashSet<>();


    @ManyToMany(mappedBy = "services")
    private Set<Client> clients;

    @Enumerated(EnumType.STRING)
    private Etat_service etatService;


    @Enumerated(EnumType.STRING)
    private Categorie categorie;

    @Enumerated(EnumType.STRING)
    private TypePaiement typePaiement;

    @ManyToOne
    @JoinColumn(name = "idType", nullable = false)
    private TypeService typeService;






}
