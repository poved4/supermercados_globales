package com.ucentral.software.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ucentral.software.order.model.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

  List<Order> findByBranch_Id(Long branchId);

}
