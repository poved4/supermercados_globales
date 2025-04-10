package com.ucentral.software.product.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.ucentral.software.exc.model.NoContentException;
import com.ucentral.software.product.model.ProductCategory;
import com.ucentral.software.product.repository.ProductCategoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductCategoryService {

  private final ProductCategoryRepository repository;

  public List<ProductCategory> getAll() {

    var iterable = StreamSupport
        .stream(repository.findAll().spliterator(), false)
        .toList();

    if (iterable.isEmpty()) {
      throw new NoContentException();
    }

    return iterable;
  }

  public ProductCategory getById(Long id) {

    var entity = repository.findById(id);

    if (!entity.isPresent()) {
      throw new NoContentException("Product Category with id " + id + " does not exist");
    }

    return entity.get();

  }

}
