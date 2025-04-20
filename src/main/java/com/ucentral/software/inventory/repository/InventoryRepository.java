package com.ucentral.software.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ucentral.software.inventory.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

  public List<Inventory> findByBranchId(Long branchId);

}
