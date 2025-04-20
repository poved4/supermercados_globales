package com.ucentral.software.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ucentral.software.order.model.entity.OrderDetail;

public interface OrderDetailsRepository extends JpaRepository<OrderDetail, Long> {

  List<OrderDetail> findByOrderId(Long orderId);

}
