package dev.alejandro.transactionalDecorator.infra.http;

import dev.alejandro.transactionalDecorator.application.ListAccountsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountHttp {

    private final ListAccountsService listAccountsService;

    public AccountHttp(ListAccountsService listAccountsService) {
        this.listAccountsService = listAccountsService;
    }


    @GetMapping
    public ResponseEntity<List<AccountResponse>> list(@RequestParam(required = false, defaultValue = "10") int limit) {
        return ResponseEntity.ok(listAccountsService.list(limit).stream().map(HttpAccountMapper::toResponse).toList());
    }

}
