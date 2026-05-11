package com.alouane.ayoub.exam.dtos;

import com.alouane.ayoub.exam.enums.CoverageLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class HealthContractDTO extends ContractDTO {
    private CoverageLevel niveauCouverture;
    private Integer nombrePersonnes;
}
