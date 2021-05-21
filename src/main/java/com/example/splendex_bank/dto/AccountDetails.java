package com.example.splendex_bank.dto;

import com.example.splendex_bank.domain.Account;

import java.util.List;
import java.util.stream.Collectors;

public class AccountDetails {

    private String username;
    private double balance;
    private List<IncomingTransferItem> myIncomingTransfers;
    private List<OutgoingTransferItem> myOutgoingTransfers;

    public AccountDetails(Account account){
        this.username= account.getUsername();
        this.balance=account.getBalance();
        this.myIncomingTransfers=account.getIncomingTransfers().stream().map(IncomingTransferItem::new).collect(Collectors.toList());
        this.myOutgoingTransfers=account.getOutgoingTransfers().stream().map(OutgoingTransferItem::new).collect(Collectors.toList());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<IncomingTransferItem> getMyIncomingTransfers() {
        return myIncomingTransfers;
    }

    public void setMyIncomingTransfers(List<IncomingTransferItem> myIncomingTransfers) {
        this.myIncomingTransfers = myIncomingTransfers;
    }

    public List<OutgoingTransferItem> getMyOutgoingTransfers() {
        return myOutgoingTransfers;
    }

    public void setMyOutgoingTransfers(List<OutgoingTransferItem> myOutgoingTransfers) {
        this.myOutgoingTransfers = myOutgoingTransfers;
    }
}
