package com.ucentral.software.auth.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ucentral.software.auth.model.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

  public Optional<Account> findByEmail(String email);

}
