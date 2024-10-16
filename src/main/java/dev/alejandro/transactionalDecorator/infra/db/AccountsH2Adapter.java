package dev.alejandro.transactionalDecorator.infra.db;

import dev.alejandro.transactionalDecorator.domain.Account;
import dev.alejandro.transactionalDecorator.domain.AccountRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AccountsH2Adapter implements AccountRepository {

    private final AccountJpaRepository repository;

    public AccountsH2Adapter(AccountJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void persist(Account account) {
        repository.save(DbAccountMapper.toAccountJpa(account));
    }

    @Override
    public Optional<Account> findAccountById(UUID accountId) {
        return repository.findById(accountId).map(DbAccountMapper::toAccount);
    }

    @Override
    public Optional<Account> findByClientId(UUID clientId) {
        return repository.findByClientId(clientId).map(DbAccountMapper::toAccount);
    }

    @Override
    public List<Account> listWithLimit(int limit) {
        return repository.listWithLimit(limit).stream().map(DbAccountMapper::toAccount).toList();
    }


}
