package com.stripe.example.demo.rentspace.controller;

import com.stripe.example.demo.rentspace.dto.OwnerRequest;
import com.stripe.example.demo.rentspace.model.Owner;
import com.stripe.example.demo.rentspace.repository.OwnerRepository;
import com.stripe.example.demo.rentspace.service.StripeService;
import com.stripe.model.Account;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountController {

    private StripeService stripeService;
    private OwnerRepository ownerRepository;

    @PostMapping("/register")
    public Owner registerOwner(@RequestBody OwnerRequest ownerRequest) throws Exception {
        // Cria uma conta conectada no Stripe
        Account account = stripeService.createConnectedAccount(ownerRequest.getEmail(), ownerRequest.getCountry());

        // Salva o proprietário do produto com o accountId do Stripe
        Owner owner = new Owner();
        owner.setName(ownerRequest.getName());
        owner.setEmail(ownerRequest.getEmail());
        owner.setCountry(ownerRequest.getCountry());
        owner.setStripeAccountId(account.getId());
        ownerRepository.save(owner);

        return owner;
    }
}

