package com.example.bricoli.dto;

import com.example.bricoli.enums.Role;
import jakarta.persistence.*;
import lombok.Data;





@Data
public class PersonneDto
{
    private int id;
    private String nom;
    private String prenom;
    private int age;
    private String genre;
    private String telephone;
    private String ville;
    private String email;
    private String password;

    private String role;


    private String cnie;

    private String zoneDeplacement;

}
