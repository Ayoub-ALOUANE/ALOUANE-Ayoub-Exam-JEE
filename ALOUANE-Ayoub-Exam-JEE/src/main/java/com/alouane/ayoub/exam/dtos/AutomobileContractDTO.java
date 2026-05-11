package com.alouane.ayoub.exam.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class AutomobileContractDTO extends ContractDTO {
    private String matricule;
    private String marque;
    private String modele;
}
