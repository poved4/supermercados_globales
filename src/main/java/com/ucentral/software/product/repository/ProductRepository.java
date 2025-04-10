package com.ucentral.software.product.repository;

import org.springframework.data.repository.CrudRepository;

import com.ucentral.software.product.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> { }
