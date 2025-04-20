package com.ucentral.software.order.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ucentral.software.exc.model.NoContentException;
import com.ucentral.software.order.model.entity.OrderDetail;
import com.ucentral.software.order.repository.OrderDetailsRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderDetailsService {

  private final OrderDetailsRepository orderDetailsRepository;

  public List<OrderDetail> getByOrderId(Long orderId) {

    var entity = orderDetailsRepository.findByOrderId(orderId);

    if (entity.isEmpty()) {
      throw new NoContentException("Order with id " + orderId + " does not exist");
    }

    return entity;

  }

}
