package com.example.splendex_bank.domain;

import com.example.splendex_bank.dto.AccountRegistrationCommand;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String encryptedPassword;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = AccountRole.class,
            fetch = FetchType.EAGER)
    @CollectionTable(name = "account_roles")
    private List<AccountRole> accountRoleList = new ArrayList<>();

    @Column(name = "balance")
    private double balance;


    @OneToMany(mappedBy = "target")
    @Column(name = "incoming_transfers")
    private List<Transfer> incomingTransfers;

    @OneToMany(mappedBy = "source")
    @Column(name = "outgoing_transfers")
    private List<Transfer> outgoingTransfers;

    public Account() {
    }

    public Account(AccountRegistrationCommand accountRegistrationCommand, String encryptedPassword) {
        this.username = accountRegistrationCommand.getUsername();
        this.encryptedPassword = encryptedPassword;
        accountRoleList.add(AccountRole.ROLE_USER);
        this.balance = 0.0;
        this.incomingTransfers = new ArrayList<>();
        this.outgoingTransfers = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public List<AccountRole> getAccountRoleList() {
        return accountRoleList;
    }

    public void setAccountRoleList(List<AccountRole> accountRoleList) {
        this.accountRoleList = accountRoleList;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transfer> getIncomingTransfers() {
        return incomingTransfers;
    }

    public void setIncomingTransfers(List<Transfer> incomingTransfers) {
        this.incomingTransfers = incomingTransfers;
    }

    public List<Transfer> getOutgoingTransfers() {
        return outgoingTransfers;
    }

    public void setOutgoingTransfers(List<Transfer> outgoingTransfers) {
        this.outgoingTransfers = outgoingTransfers;
    }
}
