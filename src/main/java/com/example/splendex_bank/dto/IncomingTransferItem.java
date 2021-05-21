package com.example.splendex_bank.dto;

import com.example.splendex_bank.domain.Transfer;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class IncomingTransferItem {

    private String source;
    private double amount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    public IncomingTransferItem(Transfer transfer) {
        this.source = transfer.getSource().getUsername();
        this.amount = transfer.getAmount();
        this.date = transfer.getTime();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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
