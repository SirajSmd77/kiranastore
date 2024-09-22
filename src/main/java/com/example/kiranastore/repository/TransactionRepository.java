package com.example.kiranastore.repository;

import com.example.kiranastore.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {

        @Query("{ 'date' : { $gte: ?0, $lte: ?1 } }")
        List<Transaction> findTransactionsByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    }

