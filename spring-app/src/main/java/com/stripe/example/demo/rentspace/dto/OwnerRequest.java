package com.stripe.example.demo.rentspace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerRequest {
    private String name;
    private String email;
    private String country;
}
