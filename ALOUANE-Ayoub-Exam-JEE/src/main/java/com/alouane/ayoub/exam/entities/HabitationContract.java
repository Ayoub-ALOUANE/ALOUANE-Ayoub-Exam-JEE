package com.alouane.ayoub.exam.entities;

import com.alouane.ayoub.exam.enums.HousingType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("HAB")
@Data @NoArgsConstructor @AllArgsConstructor
public class HabitationContract extends Contract {
    @Enumerated(EnumType.STRING)
    private HousingType typeLogement;
    private String adresse;
    private Double superficie;
}
