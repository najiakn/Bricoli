package com.example.bricoli.models;

import com.example.bricoli.enums.Categorie;
import com.example.bricoli.enums.Etat_service;
import com.example.bricoli.enums.Role;
import com.example.bricoli.enums.TypePaiement;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titre;
    @Lob
    private String description;
    private  int prix ;

    private Date date_Creation;

    @ManyToMany(mappedBy = "services")
    private Set<Prestataire> prestataires;


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
