package com.example.splendex_bank.dto;

public class DepositCommand {

    private long amount;

    public DepositCommand(long amount) {
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
