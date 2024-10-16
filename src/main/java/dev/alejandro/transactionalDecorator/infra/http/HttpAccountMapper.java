package dev.alejandro.transactionalDecorator.infra.http;


import dev.alejandro.transactionalDecorator.domain.Account;

final class HttpAccountMapper {
    private HttpAccountMapper() {
    }

    static AccountResponse toResponse(Account account) {
        return new AccountResponse(account.accountId(), account.clientId(), account.balance());
    }
}

