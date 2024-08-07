package com.stripe.example.demo.rentspace.repository;

import com.stripe.example.demo.rentspace.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
