package com.ucentral.software.branch.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.ucentral.software.branch.model.Branch;
import com.ucentral.software.branch.repository.BranchRepository;
import com.ucentral.software.exc.model.NoContentException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BranchService {

  private final BranchRepository repository;

  public List<Branch> getAll() {

    var iterable = StreamSupport
        .stream(repository.findAll().spliterator(), false)
        .toList();

    if (iterable.isEmpty()) {
      throw new NoContentException();
    }

    return iterable;
  }

  public Branch getById(Long id) {

    var entity = repository.findById(id);

    if (!entity.isPresent()) {
      throw new NoContentException("Branch with id " + id + " does not exist");
    }

    return entity.get();

  }

}
