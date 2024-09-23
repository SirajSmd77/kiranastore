package com.example.kiranastore;

import com.example.kiranastore.dto.TransactionDTO;
import com.example.kiranastore.entity.Transaction;
import com.example.kiranastore.entity.TransactionType;
import com.example.kiranastore.repository.TransactionRepository;
import com.example.kiranastore.service.ExchangeRateService;
import com.example.kiranastore.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class TransactionServiceTest {

        @InjectMocks
        private TransactionService transactionService;


       @Mock
       private ExchangeRateService currencyConversionService;

        @Mock
        private TransactionRepository transactionRepository;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this); // Initialize mocks
        }

    @Test
    public void testCreateTransaction_Success() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount(1000);
        transactionDTO.setType(TransactionType.DEPOSIT);
        transactionDTO.setCurrency("USD");
        Transaction transaction = new Transaction(transactionDTO, 75000);

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction result = transactionService.createTransaction(transactionDTO);

        assertNotNull(result);
        assertEquals(1000, result.getAmount(), 0); // Ensure the amount is 1000
        assertEquals("USD", result.getCurrency());
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }



    @Test
        public void testGetTransactionById_Success() {
            Transaction transaction = new Transaction();
            transaction.setId("1");
            when(transactionRepository.findById("1")).thenReturn(Optional.of(transaction));

            Optional<Transaction> result = transactionService.getTransactionById("1");

            assertTrue(result.isPresent());
            assertEquals("1", result.get().getId());
        }
 }


