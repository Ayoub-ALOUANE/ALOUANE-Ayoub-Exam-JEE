package com.alouane.ayoub.exam.dtos;

import com.alouane.ayoub.exam.enums.ContractStatus;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AutomobileContractDTO.class, name = "AUT"),
        @JsonSubTypes.Type(value = HabitationContractDTO.class, name = "HAB"),
        @JsonSubTypes.Type(value = HealthContractDTO.class, name = "SAN")
})
@Data @NoArgsConstructor @AllArgsConstructor
public abstract class ContractDTO {
    private Long id;
    private LocalDate dateSouscription;
    private ContractStatus statut;
    private LocalDate dateValidation;
    private Double montantCotisation;
    private Integer duree;
    private Double tauxCouverture;
    private String type; // For easy identification in frontend
    private ClientDTO client;
}
