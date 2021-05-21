package com.example.splendex_bank.dto;

public class TransferFormCommand {

    private String target;
    private double amount;

    public TransferFormCommand(String target, double amount) {
        this.target = target;
        this.amount = amount;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
