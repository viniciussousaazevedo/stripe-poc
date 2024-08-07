package com.stripe.example.demo.tutorial;

import com.stripe.model.Product;
import lombok.Getter;

@Getter
public class RequestDTO {
    Product[] items;
    String customerName;
    String customerEmail;

}