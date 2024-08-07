package com.stripe.example.demo.rentspace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentRequest {
    private Long amount;
    private String currency;
    private String stripeAccountId;
}
