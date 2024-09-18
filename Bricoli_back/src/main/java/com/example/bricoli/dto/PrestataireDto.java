package com.example.bricoli.dto;

import lombok.Data;

import java.util.Set;

@Data
public class PrestataireDto extends PersonneDto{

    private String profil;
    private String cnie;
    private String zoneDeplacement;
}
