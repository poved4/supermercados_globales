package com.ucentral.software.branch.repository;

import org.springframework.data.repository.CrudRepository;

import com.ucentral.software.branch.model.Branch;

public interface BranchRepository extends CrudRepository<Branch, Long> { }
