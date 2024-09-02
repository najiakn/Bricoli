package com.example.bricoli.dto;

import lombok.Data;

import java.util.Set;

@Data
public class PrestataireDto extends PersonneDto{
    private String zoneDeplacement;
    private byte[] profil;
    private byte[] cnie;
    private Set<Integer> serviceIds;
}
