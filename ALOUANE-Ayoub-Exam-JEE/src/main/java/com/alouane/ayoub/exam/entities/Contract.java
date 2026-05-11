package com.alouane.ayoub.exam.entities;

import com.alouane.ayoub.exam.enums.ContractStatus;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", length = 3)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AutomobileContract.class, name = "AUT"),
        @JsonSubTypes.Type(value = HabitationContract.class, name = "HAB"),
        @JsonSubTypes.Type(value = HealthContract.class, name = "SAN")
})
@Data @NoArgsConstructor @AllArgsConstructor
public abstract class Contract {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateSouscription;
    
    @Enumerated(EnumType.STRING)
    private ContractStatus statut;
    
    private LocalDate dateValidation;
    private Double montantCotisation;
    private Integer duree;
    private Double tauxCouverture;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "contract", fetch = FetchType.LAZY)
    private List<Payment> paiements;
}
