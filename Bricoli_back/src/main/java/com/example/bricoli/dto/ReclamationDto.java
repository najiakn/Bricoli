package com.example.bricoli.dto;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReclamationDto {
    private int id;
    private String titre;
    private String description;
    private Date date;
    private int clientId;
    private int prestataireId;
}
