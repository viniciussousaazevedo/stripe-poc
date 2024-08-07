package com.stripe.example.demo.rentspace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private String description;
    private Long price;
    private Long ownerId;
    private String stripeAccountId;

}
