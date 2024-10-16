package dev.alejandro.transactionalDecorator;

import dev.alejandro.transactionalDecorator.application.DefaultTransferService;
import dev.alejandro.transactionalDecorator.application.ListAccountsService;
import dev.alejandro.transactionalDecorator.application.TransferService;
import dev.alejandro.transactionalDecorator.domain.AccountRepository;
import dev.alejandro.transactionalDecorator.infra.TransactionalTransferServiceDecorator;
import dev.alejandro.transactionalDecorator.infra.db.AccountJpaRepository;
import dev.alejandro.transactionalDecorator.infra.db.AccountsH2Adapter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TransactionalDecoratorApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder()
				.sources(TransactionalDecoratorApplication.class)
				.run(args);
	}

	@Bean
	AccountRepository accounts(AccountJpaRepository accountJpaRepository) {
		return new AccountsH2Adapter(accountJpaRepository);
	}

	@Bean
	ListAccountsService listAccountsService(AccountRepository accountRepository) {
		return new ListAccountsService(accountRepository);
	}

	@Bean
	TransferService transferService(AccountRepository accountRepository) {
		return new DefaultTransferService(accountRepository);
	}

	@Bean(name = "transactional")
	TransferService transferServiceTransactional(TransferService transferService) {
		return new TransactionalTransferServiceDecorator(transferService);
	}

}
