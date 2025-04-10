package com.ucentral.software.product.repository;

import org.springframework.data.repository.CrudRepository;

import com.ucentral.software.product.model.ProductCategory;

public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> { }
