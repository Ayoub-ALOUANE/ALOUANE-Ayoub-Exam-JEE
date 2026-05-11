package com.alouane.ayoub.exam.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("AUT")
@Data @NoArgsConstructor @AllArgsConstructor
public class AutomobileContract extends Contract {
    private String matricule;
    private String marque;
    private String modele;
}
