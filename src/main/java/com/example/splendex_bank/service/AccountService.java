package com.example.splendex_bank.service;


import com.example.splendex_bank.domain.Account;
import com.example.splendex_bank.dto.AccountDetails;
import com.example.splendex_bank.dto.AccountRegistrationCommand;
import com.example.splendex_bank.exception.AccountLimitExceededException;
import com.example.splendex_bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AccountService {

    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String createNewAccount(AccountRegistrationCommand accountRegistrationCommand) {

        String encryptedPassword = passwordEncoder.encode(accountRegistrationCommand.getPassword());
        Account newAccount = accountRepository.save(new Account(accountRegistrationCommand, encryptedPassword));
        return "New account was create with username : " + newAccount.getUsername() + " and id: " + newAccount.getId();
    }


    public double deposit(double amount, String username) {
        Account account = findByUsername(username);
        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
        return accountRepository.save(account).getBalance();
    }

    public double withdraw(double amount, String username) throws AccountLimitExceededException {
        Account account = findByUsername(username);

        if (account.getBalance() >= amount) {
            double newBalance = account.getBalance() - amount;
            account.setBalance(newBalance);
            return accountRepository.save(account).getBalance();
        } else {
            throw new AccountLimitExceededException("It's over your limit, withdraw denied!");
        }


    }

    public Account findByUsername(String username) {
        Optional<Account> optionalAccount = accountRepository.findByUsername(username);
        if (optionalAccount.isPresent()) {
            return optionalAccount.get();
        } else {
            throw new EntityNotFoundException();
        }
    }

    public AccountDetails getMyAccountDetails(String name) {
        Account myAccount = findByUsername(name);
        return new AccountDetails(myAccount);
    }

}
