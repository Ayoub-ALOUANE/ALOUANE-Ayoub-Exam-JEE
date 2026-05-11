package com.alouane.ayoub.exam.dtos;

import com.alouane.ayoub.exam.enums.HousingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class HabitationContractDTO extends ContractDTO {
    private HousingType typeLogement;
    private String adresse;
    private Double superficie;
}
