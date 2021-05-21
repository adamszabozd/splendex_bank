package com.example.splendex_bank.service;


import com.example.splendex_bank.domain.Account;
import com.example.splendex_bank.domain.Transfer;
import com.example.splendex_bank.dto.TransferFormCommand;
import com.example.splendex_bank.exception.AccountLimitExceededException;
import com.example.splendex_bank.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TransferService {

    private final TransferRepository transferRepository;
    private final AccountService accountService;

    @Autowired
    public TransferService(TransferRepository transferRepository, AccountService accountService) {
        this.transferRepository = transferRepository;
        this.accountService = accountService;
    }

    public void createNewTransfer(TransferFormCommand transferFormCommand, String username) throws AccountLimitExceededException {

        Account source = accountService.findByUsername(username);
        Account target = accountService.findByUsername(transferFormCommand.getTarget());
        double transferAmount = transferFormCommand.getAmount();

        accountService.withdraw(transferAmount, username);
        accountService.deposit(transferAmount, transferFormCommand.getTarget());

        transferRepository.save(new Transfer(source, target, transferAmount));
    }
}
