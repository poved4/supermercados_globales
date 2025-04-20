package com.ucentral.software.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ucentral.software.order.model.dto.UpdateOrderStatusRequest;
import com.ucentral.software.order.service.OrderDetailsService;
import com.ucentral.software.order.service.OrderService;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
public class OrderController {

  private final OrderService orderService;
  private final OrderDetailsService orderDetailsService;

  @GetMapping
  public ResponseEntity<?> endpoint() {
    return ResponseEntity.ok(orderService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> endpointOrder(@PathVariable @Positive Long id) {
    return ResponseEntity.ok(orderService.getById(id));
  }

  @GetMapping("/{id}/details")
  public ResponseEntity<?> endPointOrderDetails(@PathVariable @Positive Long id) {
    var orders = orderDetailsService.getByOrderId(id);
    return ResponseEntity.ok(orders);
  }

  @PutMapping("/{id}/status")
  public ResponseEntity<?> updateOrderStatus(@PathVariable @Positive Long id, @RequestBody UpdateOrderStatusRequest request) {
    var updatedOrder = orderService.updateStatus(id, request.statusId());
    return ResponseEntity.ok(updatedOrder);
  }

  @GetMapping("/branch/{id}")
  public ResponseEntity<?> endpoint(@PathVariable @Positive Long id) {
    return ResponseEntity.ok(orderService.getByBranchId(id));
  }

}
