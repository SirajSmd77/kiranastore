package com.example.kiranastore.controller;

import com.example.kiranastore.dto.TransactionDTO;
import com.example.kiranastore.entity.Transaction;
import com.example.kiranastore.entity.TransactionType;
import com.example.kiranastore.service.RateLimited;
import com.example.kiranastore.service.TransactionService;
import com.example.kiranastore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TransactionController {

    @Autowired
    UserService userService;

    public TransactionController(UserService userService) {
        this.userService = userService;
    }


    @Autowired
    private TransactionService transactionService;

    @GetMapping("api/transaction")
   @RateLimited(limit = 10, period = 60) // Custom rate limiting annotation
    public ResponseEntity<Transaction> createTransaction(
            @RequestParam double amount,
            @RequestParam String currency,
            @RequestParam TransactionType type) {

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount(amount);
        transactionDTO.setCurrency(currency);
        transactionDTO.setType(type);

        Transaction createdTransaction = transactionService.createTransaction(transactionDTO);

        return ResponseEntity.ok(createdTransaction);
    }


        @PostMapping("/register")
        public String registerUser(
                @RequestParam String username,
                @RequestParam String password,
                @RequestParam String role) {
            userService.registerUser(username, password, role);
            return "User registered successfully!";
        }
    }
