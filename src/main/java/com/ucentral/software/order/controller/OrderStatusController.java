package com.ucentral.software.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ucentral.software.order.service.OrderStatusService;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/orders/status")
public class OrderStatusController {

  private final OrderStatusService orderStatusService;

  @GetMapping
  public ResponseEntity<?> endpointOrderStatus() {
    return ResponseEntity.ok(orderStatusService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> endpointOrderStatus(@PathVariable @Positive Long id) {
    return ResponseEntity.ok(orderStatusService.getById(id));
  }

}
