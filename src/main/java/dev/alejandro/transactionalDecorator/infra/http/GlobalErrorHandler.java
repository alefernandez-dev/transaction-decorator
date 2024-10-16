package dev.alejandro.transactionalDecorator.infra.http;

import dev.alejandro.transactionalDecorator.application.AccountNotFoundForTransferError;
import dev.alejandro.transactionalDecorator.application.LowBalanceError;
import dev.alejandro.transactionalDecorator.application.TransferToSameAccountError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandler {

    private final static Logger log = LoggerFactory.getLogger(GlobalErrorHandler.class);

    @ExceptionHandler({LowBalanceError.class, AccountNotFoundForTransferError.class, TransferToSameAccountError.class})
    public ResponseEntity<MessageResponse> errorHandler(RuntimeException e) {

        return switch (e) {
            case LowBalanceError lbe -> ResponseEntity.badRequest().body(new MessageResponse("insufficient balance to perform the operation", 400));
            case AccountNotFoundForTransferError anf -> ResponseEntity.badRequest().body(new MessageResponse("transfer account with id: " + anf.getAccountId() + "not found", 404));
            case TransferToSameAccountError tae -> ResponseEntity.badRequest().body(new MessageResponse("same account to transfer", 400));
            default -> throw new IllegalStateException(e);
        };

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> handlerException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.internalServerError().build();
    }

}
