package com.alouane.ayoub.exam.entities;

import com.alouane.ayoub.exam.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private Double montant;
    
    @Enumerated(EnumType.STRING)
    private PaymentType type;

    @ManyToOne
    private Contract contract;
}
