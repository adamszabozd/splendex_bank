package com.example.splendex_bank.dto;

public class WithDrawCommand {

    private double amount;

    public WithDrawCommand(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
