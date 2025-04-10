package com.ucentral.software.supplier.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ucentral.software.supplier.service.SupplierService;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/suppliers")
public class SupplierController {

  private final SupplierService service;

  @GetMapping
  public ResponseEntity<?> endpoint() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> endpoint(@PathVariable @Positive Long id) {
    return ResponseEntity.ok(service.getById(id));
  }

}
