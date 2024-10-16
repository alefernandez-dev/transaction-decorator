package dev.alejandro.transactionalDecorator.infra;

import dev.alejandro.transactionalDecorator.application.AccountNotFoundForTransferError;
import dev.alejandro.transactionalDecorator.application.LowBalanceError;
import dev.alejandro.transactionalDecorator.application.TransferService;
import dev.alejandro.transactionalDecorator.application.TransferToSameAccountError;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Transactional
public class TransactionalTransferServiceDecorator implements TransferService {
    private final TransferService transferService;

    public TransactionalTransferServiceDecorator(TransferService transferService) {
        this.transferService = transferService;
    }

    @Override
    public void transfer(UUID accountIdFrom, UUID accountIdTo, BigDecimal amount) throws TransferToSameAccountError, AccountNotFoundForTransferError, LowBalanceError {
        transferService.transfer(accountIdFrom, accountIdTo, amount);
    }
}
