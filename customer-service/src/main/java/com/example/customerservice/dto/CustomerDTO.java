package com.example.customerservice.dto;

import jakarta.persistence.*;
import lombok.*;
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder @ToString
public class CustomerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
