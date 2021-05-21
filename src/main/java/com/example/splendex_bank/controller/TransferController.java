package com.example.splendex_bank.controller;

import com.example.splendex_bank.dto.TransferFormCommand;
import com.example.splendex_bank.exception.AccountLimitExceededException;
import com.example.splendex_bank.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private final TransferService transferService;

    @Autowired
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<Void> createNewTransfer(@RequestBody TransferFormCommand transferFormCommand, Principal principal) throws AccountLimitExceededException {
        transferService.createNewTransfer(transferFormCommand, principal.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
