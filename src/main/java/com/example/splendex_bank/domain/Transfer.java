package com.example.splendex_bank.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source")
    private Account source;

    @ManyToOne
    @JoinColumn(name = "target")
    private Account target;

    @Column(name = "amount")
    private double amount;

    @Column(name = "time")
    private LocalDateTime time;

    public Transfer() {
    }

    public Transfer(Account source, Account target, double amount) {

        this.source = source;
        this.target = target;
        this.amount = amount;
        this.time = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getSource() {
        return source;
    }

    public void setSource(Account source) {
        this.source = source;
    }

    public Account getTarget() {
        return target;
    }

    public void setTarget(Account target) {
        this.target = target;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
