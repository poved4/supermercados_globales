package com.ucentral.software.product.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ucentral.software.product.service.ProductService;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {

  private final ProductService service;

  @GetMapping
  public ResponseEntity<?> endpoint() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> endpoint(@PathVariable @Positive Long id) {
    return ResponseEntity.ok(service.getById(id));
  }

}
