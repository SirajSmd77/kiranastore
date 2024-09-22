package com.example.kiranastore.service;

import com.example.kiranastore.dto.ReportDTO;
import com.example.kiranastore.entity.Transaction;
import com.example.kiranastore.entity.TransactionType;
import com.example.kiranastore.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ReportService {


    private static final Logger log = LoggerFactory.getLogger(ReportService.class);
    @Autowired
        private TransactionRepository transactionRepository;


        public ReportDTO generateWeeklyReport() {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime lastWeek = now.minusDays(7);

            List<Transaction> transactions = transactionRepository.findTransactionsByDateRange(lastWeek, now);
           log.info("weekly report :{}",transactions);
            return generateReport(transactions);
        }

        public ReportDTO generateMonthlyReport() {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime lastMonth = now.minusDays(30);

            List<Transaction> transactions = transactionRepository.findTransactionsByDateRange(lastMonth, now);
            log.info("monthly report :{}",transactions);

            return generateReport(transactions);
        }

        public ReportDTO generateYearlyReport() {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime lastYear = now.minusDays(365);

            List<Transaction> transactions = transactionRepository.findTransactionsByDateRange(lastYear, now);
            log.info("yearly report :{}",transactions);
            return generateReport(transactions);
        }

    private ReportDTO generateReport(List<Transaction> transactions) {
        double totalCredits = transactions.stream()
                .filter(t -> t.getType() == TransactionType.DEPOSIT)
                .mapToDouble(Transaction::getAmountInINR)
                .sum();

        double totalDebits = transactions.stream()
                .filter(t -> t.getType() == TransactionType.WITHDRAWAL)
                .mapToDouble(Transaction::getAmountInINR)
                .sum();

        return new ReportDTO(totalCredits, totalDebits, totalCredits - totalDebits);
    }

}
