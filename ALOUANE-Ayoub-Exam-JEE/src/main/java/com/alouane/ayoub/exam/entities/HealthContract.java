package com.alouane.ayoub.exam.entities;

import com.alouane.ayoub.exam.enums.CoverageLevel;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("SAN")
@Data @NoArgsConstructor @AllArgsConstructor
public class HealthContract extends Contract {
    @Enumerated(EnumType.STRING)
    private CoverageLevel niveauCouverture;
    private Integer nombrePersonnes;
}
