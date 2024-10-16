package dev.alejandro.transactionalDecorator.application;


import dev.alejandro.transactionalDecorator.domain.Account;
import dev.alejandro.transactionalDecorator.domain.AccountRepository;

import java.util.List;

public class ListAccountsService {

    private final AccountRepository repository;

    public ListAccountsService(AccountRepository repository) {
        this.repository = repository;
    }


    public List<Account> list(int limit) {
            return repository.listWithLimit(limit);
    }

}
