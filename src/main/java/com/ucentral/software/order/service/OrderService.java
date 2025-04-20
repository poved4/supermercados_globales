package com.ucentral.software.order.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ucentral.software.exc.model.NoContentException;
import com.ucentral.software.order.model.entity.Order;
import com.ucentral.software.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final OrderStatusService orderStatusService;

  public List<Order> getAll() {
    return orderRepository.findAll();
  }

  public Order getById(Long id) {

    var entity = orderRepository.findById(id);

    if (!entity.isPresent()) {
      throw new NoContentException("Order with id " + id + " does not exist");
    }

    return entity.get();

  }

  public List<Order> getByBranchId(Long branchId) {

    var entity = orderRepository.findByBranch_Id(branchId);

    if (entity.isEmpty()) {
      throw new NoContentException("Order with id " + branchId + " does not exist");
    }

    return entity;

  }

  public Order updateStatus(Long orderId, Long statusId) {
    var orderOpt = orderRepository.findById(orderId);
    if (orderOpt.isEmpty()) {
      throw new NoContentException("Order with id " + orderId + " does not exist");
    }

    var statusOpt = orderStatusService.getById(statusId);
    if (statusOpt == null) {
      throw new NoContentException("OrderStatus with id " + statusId + " does not exist");
    }

    var order = orderOpt.get();
    var newStatus = statusOpt;

    order.setStatus(newStatus);

    if ("ENTREGADO".equalsIgnoreCase(newStatus.getName())) {
      order.setDeliveryDate(LocalDateTime.now());
    }

    var updatedOrder = orderRepository.save(order);

    log.info("Updated status of order {} to {}", orderId, newStatus.getName());
    return updatedOrder;
  }

}
