package com.bruno.cqrs.service;

import com.bruno.cqrs.command.CreateAccountCommand;
import com.bruno.cqrs.command.CreditMoneyCommand;
import com.bruno.cqrs.command.DebitMoneyCommand;
import com.bruno.cqrs.dto.AccountCreationDTO;
import com.bruno.cqrs.dto.MoneyAmountDTO;
import com.bruno.cqrs.entity.BankAccount;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.bruno.cqrs.service.ServiceUtils.formatUuid;

@Service
@AllArgsConstructor
public class AccountCommandService {
    private final CommandGateway commandGateway;

    public CompletableFuture<BankAccount> createAccount(AccountCreationDTO creationDTO) {
        return this.commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID(),
                creationDTO.getInitialBalance(),
                creationDTO.getOwner()
        ));
    }

    public CompletableFuture<String> creditMoneyToAccount(String accountId, MoneyAmountDTO moneyCreditDTO) {
        return this.commandGateway.send(new CreditMoneyCommand(formatUuid(accountId), moneyCreditDTO.getAmount()));
    }

    public CompletableFuture<String> debitMoneyFromAccount(String accountId, MoneyAmountDTO moneyDebitDTO) {
        return this.commandGateway.send(new DebitMoneyCommand(formatUuid(accountId), moneyDebitDTO.getAmount()));
    }
}
