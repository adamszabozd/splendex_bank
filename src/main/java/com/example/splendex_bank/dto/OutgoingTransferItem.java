package com.example.splendex_bank.dto;

import com.example.splendex_bank.domain.Transfer;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class OutgoingTransferItem {

    private String target;
    private double amount;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    public OutgoingTransferItem(Transfer transfer){
        this.target =transfer.getTarget().getUsername();
        this.amount=transfer.getAmount();
        this.date=transfer.getTime();
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
