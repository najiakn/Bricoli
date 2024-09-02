package com.example.bricoli.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ClientDto extends PersonneDto {
    private Set<Integer> serviceId;
}
