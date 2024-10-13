package com.example.bricoli.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Prestataire extends Personne {





    @ManyToMany
    @JoinTable(
            name = "prestataire_service",
            joinColumns = @JoinColumn(name = "prestataire_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private Set<ServiceModel> services = new HashSet<>();


}
