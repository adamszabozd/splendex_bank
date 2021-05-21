package com.example.splendex_bank.exception;

public class AccountLimitExceededException extends Exception {

    public AccountLimitExceededException(String message) {
        super(message);
    }
}
