package com.example.kiranastore.entity;

import com.example.kiranastore.dto.TransactionDTO;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "transactions") // MongoDB collection annotation
public class Transaction {

    @Id
    private String id;  // MongoDB document ID

    private double amount;
    private String currency;
    private TransactionType type;
    private double amountInINR;
    private LocalDateTime date;

    // Constructor to map from DTO
    public Transaction(TransactionDTO transactionDTO, double amountInINR) {
        this.amount = transactionDTO.getAmount();
        this.currency = transactionDTO.getCurrency();
        this.amountInINR = amountInINR;
        this.type = transactionDTO.getType();
        this.date = LocalDateTime.now();  // Automatically sets the current date
    }

    public Transaction() {}

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmountInINR() {
        return amountInINR;
    }

    public void setAmountInINR(double amountInINR) {
        this.amountInINR = amountInINR;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
