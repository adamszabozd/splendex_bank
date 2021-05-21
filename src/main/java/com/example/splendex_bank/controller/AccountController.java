package com.example.splendex_bank.controller;


import com.example.splendex_bank.dto.*;
import com.example.splendex_bank.exception.AccountLimitExceededException;
import com.example.splendex_bank.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Void> createNewAccount(@RequestBody AccountRegistrationCommand accountRegistrationCommand) {
        String newData = accountService.createNewAccount(accountRegistrationCommand);
        logger.info(newData);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/deposit")
    public ResponseEntity<Void> deposit(@RequestBody DepositCommand depositCommand, Principal principal) {
        double newBalance = accountService.deposit(depositCommand.getAmount(), principal.getName());
        logger.info("Your balance was raised by: " + depositCommand.getAmount() + " new balance: " + newBalance);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Void> withdraw(@RequestBody WithDrawCommand withDrawCommand, Principal principal) throws AccountLimitExceededException {
        double newBalance = accountService.withdraw(withDrawCommand.getAmount(), principal.getName());
        logger.info("Your balance was decreased by: " + withDrawCommand.getAmount() + " new balance: " + newBalance);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/transactionHistory")
    public ResponseEntity<AccountDetails> getMyAccountDetails(Principal principal) {
        AccountDetails myAccountDetails = accountService.getMyAccountDetails(principal.getName());
        return new ResponseEntity<>(myAccountDetails, HttpStatus.CREATED);
    }


    @GetMapping("printHistory")
    public ResponseEntity<Void> printMyHistory(Principal principal) {

        AccountDetails myAccountDetails = accountService.getMyAccountDetails(principal.getName());

        for (IncomingTransferItem item : myAccountDetails.getMyIncomingTransfers()) {
            logger.info("Incoming transfer from: " + item.getSource() + " , amount: " + item.getAmount());
        }

        for (OutgoingTransferItem item : myAccountDetails.getMyOutgoingTransfers()) {
            logger.info("Outgoing transfer to: " + item.getTarget() + " , amount: " + item.getAmount());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

