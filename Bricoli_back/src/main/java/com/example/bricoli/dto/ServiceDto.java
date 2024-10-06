package com.example.bricoli.dto;
import com.example.bricoli.enums.Categorie;
import com.example.bricoli.enums.Etat_service;
import com.example.bricoli.enums.TypePaiement;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateCreation;
    private Etat_service etatService;
    private Categorie categorie;
    private TypePaiement typePaiement;
    private int idType;
    private String imageUrl;

}
