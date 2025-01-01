package org.eazybytes.accounts.repository;

import java.util.Optional;
import org.eazybytes.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
  Optional<Account> findByCustomerId(Long customerId);
}
