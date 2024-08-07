package com.stripe.example.demo.rentspace.controller;

import com.stripe.example.demo.rentspace.model.Product;
import com.stripe.example.demo.rentspace.repository.ProductRepository;
import com.stripe.example.demo.rentspace.service.StripeService;
import com.stripe.example.demo.rentspace.dto.ProductRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private StripeService stripeService;
    private ProductRepository productRepository;

    @PostMapping("/create")
    public Product createProduct(@RequestBody ProductRequest productRequest) throws Exception {
        // Cria um produto na conta conectada no Stripe
        com.stripe.model.Product stripeProduct = stripeService.createProduct(
                productRequest.getStripeAccountId(),
                productRequest.getName(),
                productRequest.getDescription()
        );

        // Salva o produto no banco de dados
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setOwnerId(productRequest.getOwnerId());
        product.setStripeProductId(stripeProduct.getId());
        productRepository.save(product);

        return product;
    }
}
