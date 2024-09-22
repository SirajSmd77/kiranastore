package com.example.kiranastore.dto;

import com.example.kiranastore.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class TransactionDTO {

        private double amount;
        private String currency;
        private TransactionType type;
        private double amountInINR;
        private LocalDateTime date;

    public TransactionDTO(String currency, double amount, TransactionType type, double amountInINR, LocalDateTime date) {
        this.currency = currency;
        this.amount = amount;
        this.type = type;
        this.amountInINR = amountInINR;
        this.date = date;
    }

    public TransactionDTO() {
    }

    public String getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }
    public TransactionType getType() {
        return type;
   }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getAmountInINR() {
        return amountInINR;
    }

    public void setAmountInINR(double amountInINR) {
        this.amountInINR = amountInINR;
    }
}
