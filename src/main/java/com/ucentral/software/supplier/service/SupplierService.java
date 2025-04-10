package com.ucentral.software.supplier.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.ucentral.software.exc.model.NoContentException;
import com.ucentral.software.supplier.model.Supplier;
import com.ucentral.software.supplier.repository.SupplierRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SupplierService {

  private final SupplierRepository repository;

  public List<Supplier> getAll() {

    var iterable = StreamSupport
        .stream(repository.findAll().spliterator(), false)
        .toList();

    if (iterable.isEmpty()) {
      throw new NoContentException();
    }

    return iterable;
  }

  public Supplier getById(Long id) {

    var entity = repository.findById(id);

    if (!entity.isPresent()) {
      throw new NoContentException("Supplier with id " + id + " does not exist");
    }

    return entity.get();

  }

}
