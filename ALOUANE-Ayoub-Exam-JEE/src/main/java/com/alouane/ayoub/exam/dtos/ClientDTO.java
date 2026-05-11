package com.alouane.ayoub.exam.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ClientDTO {
    private Long id;
    private String nom;
    private String email;
}
