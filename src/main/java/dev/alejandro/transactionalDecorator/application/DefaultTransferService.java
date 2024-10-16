package dev.alejandro.transactionalDecorator.application;


import dev.alejandro.transactionalDecorator.domain.AccountRepository;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

public class DefaultTransferService implements TransferService {

    private final AccountRepository repository;

    public DefaultTransferService(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public void transfer(UUID accountIdFrom, UUID accountIdTo, BigDecimal amount) throws TransferToSameAccountError, AccountNotFoundForTransferError, LowBalanceError {

        if (accountIdFrom.equals(accountIdTo)) {
            throw new TransferToSameAccountError();
        }

        var accountFrom = repository.findAccountById(accountIdFrom).orElseThrow(() -> new AccountNotFoundForTransferError(accountIdFrom));
        var accountTo = repository.findAccountById(accountIdTo).orElseThrow(() -> new AccountNotFoundForTransferError(accountIdTo));

        if (!accountFrom.hasSufficientBalance(amount)) {
            throw new LowBalanceError();
        }

        var newAccountFrom = accountFrom.withdraw(amount);
        var newAccountTo = accountTo.deposit(amount);

        repository.persist(newAccountFrom);

        // This block is present solely to throw an exception and test the transaction
        if((new Random().nextInt(10) + 1) < 8 ) {
            throw new RuntimeException("upss!");
        }

        repository.persist(newAccountTo);

    }
}
