package com.example.kiranastore.service;

import com.example.kiranastore.dto.TransactionDTO;
import com.example.kiranastore.entity.Transaction;
import com.example.kiranastore.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

        @Autowired
        private TransactionRepository transactionRepository;

       @Autowired
       ExchangeRateService exchangeRateService;



        public Transaction createTransaction(TransactionDTO transactionDTO) {
            String  currency=transactionDTO.getCurrency();
            Double rate = exchangeRateService.getExchangeRate(currency);
            double amountInINR = transactionDTO.getAmount() * rate;
            Transaction transaction = new Transaction(transactionDTO, amountInINR);
            transactionRepository.save(transaction);
            return transaction;
        }

    public Optional<Transaction> getTransactionById(String id) {
        return transactionRepository.findById(id);
    }

}