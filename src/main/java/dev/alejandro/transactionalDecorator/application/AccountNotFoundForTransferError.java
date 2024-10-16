package dev.alejandro.transactionalDecorator.application;

import java.util.UUID;

public class AccountNotFoundForTransferError extends RuntimeException{
    private final UUID accountId;

    public AccountNotFoundForTransferError(UUID accountId) {
        this.accountId = accountId;
    }

    public UUID getAccountId() {
        return accountId;
    }
}
