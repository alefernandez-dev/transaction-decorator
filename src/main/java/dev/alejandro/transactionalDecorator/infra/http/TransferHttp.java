package dev.alejandro.transactionalDecorator.infra.http;

import dev.alejandro.transactionalDecorator.application.TransferService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/transfer")
public class TransferHttp {

    private final TransferService transactionalTransferService;
    private final TransferService transferService;

    public TransferHttp(@Qualifier("transactional") TransferService transactionalTransferService, TransferService transferService) {
        this.transactionalTransferService = transactionalTransferService;
        this.transferService = transferService;
    }

    @PostMapping("/transactional")
    public ResponseEntity<MessageResponse> transactionalTransfer(@RequestBody TransferRequest request) {
        if (request.isInvalid()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("invalid data transfer", 400));
        }
        transactionalTransferService.transfer(UUID.fromString(request.accountIdFrom()), UUID.fromString(request.accountIdTo()), request.amount());
        return ResponseEntity.ok(new MessageResponse("successful transfer", 200));
    }

    @PostMapping
    public ResponseEntity<MessageResponse> transfer(@RequestBody TransferRequest request) {
        if (request.isInvalid()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("invalid data transfer", 400));
        }
        transferService.transfer(UUID.fromString(request.accountIdFrom()), UUID.fromString(request.accountIdTo()), request.amount());
        return ResponseEntity.ok(new MessageResponse("successful transfer", 200));
    }

}
