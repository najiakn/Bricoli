package com.example.bricoli.dto;
import com.example.bricoli.enums.Categorie;
import com.example.bricoli.enums.Etat_service;
import com.example.bricoli.enums.TypePaiement;
import lombok.*;

import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceDto {
    private int id;
    private String titre;
    private String description;
    private int prix;
    private Date dateCreation;
    private Etat_service etatService;
    private Categorie categorie;
    private TypePaiement typePaiement;
    private int idType;
    private String imageUrl;
    private String cloudinaryImageId;

}
