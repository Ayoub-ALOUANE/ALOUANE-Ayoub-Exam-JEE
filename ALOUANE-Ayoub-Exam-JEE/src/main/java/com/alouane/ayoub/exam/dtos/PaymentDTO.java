package com.alouane.ayoub.exam.dtos;

import com.alouane.ayoub.exam.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor
public class PaymentDTO {
    private Long id;
    private LocalDate date;
    private Double montant;
    private PaymentType type;
}
