package com.ucentral.software.order.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.ucentral.software.exc.model.NoContentException;
import com.ucentral.software.order.model.entity.OrderStatus;
import com.ucentral.software.order.repository.OrderStatusRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderStatusService {

  private final OrderStatusRepository repository;

  public List<OrderStatus> getAll() {

    var iterable = StreamSupport
        .stream(repository.findAll().spliterator(), false)
        .toList();

    if (iterable.isEmpty()) {
      throw new NoContentException();
    }

    return iterable;
  }

  public OrderStatus getById(Long id) {

    var entity = repository.findById(id);

    if (!entity.isPresent()) {
      throw new NoContentException("Order status with id " + id + " does not exist");
    }

    return entity.get();

  }

}
