package dev.alejandro.transactionalDecorator.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    void persist(Account account);
    Optional<Account> findAccountById(UUID accountId);
    Optional<Account> findByClientId(UUID clientId);
    List<Account> listWithLimit(int limit);
}
