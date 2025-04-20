package com.ucentral.software.order.repository;

import org.springframework.data.repository.CrudRepository;

import com.ucentral.software.order.model.entity.OrderStatus;

public interface OrderStatusRepository extends CrudRepository<OrderStatus, Long> { }
