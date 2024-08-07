package com.stripe.example.demo.rentspace.controller;

import com.stripe.example.demo.rentspace.dto.PaymentRequest;
import com.stripe.example.demo.rentspace.service.StripeService;
import com.stripe.model.PaymentIntent;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PaymentController {

    private StripeService stripeService;

    @PostMapping("/create-payment-intent")
    public PaymentIntent createPaymentIntent(@RequestBody PaymentRequest paymentRequest) throws Exception {
        return stripeService.createPaymentIntent(paymentRequest.getAmount(), paymentRequest.getCurrency(), paymentRequest.getStripeAccountId());
    }
}


