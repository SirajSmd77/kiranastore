package com.example.kiranastore.controller;

import com.example.kiranastore.dto.ReportDTO;
import com.example.kiranastore.service.RateLimited;
import com.example.kiranastore.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportController {


        @Autowired
        private ReportService reportService;

        @GetMapping("/weekly")
        @RateLimited(limit = 10, period = 60) // Custom rate limiting annotation
        public ResponseEntity<ReportDTO> getWeeklyReport() {
            return ResponseEntity.ok(reportService.generateWeeklyReport());
        }

        @GetMapping("/monthly")
        @RateLimited(limit = 10, period = 60) // Custom rate limiting annotation
        public ResponseEntity<ReportDTO> getMonthlyReport() {
            return ResponseEntity.ok(reportService.generateMonthlyReport());
        }

        @GetMapping("/yearly")
        @RateLimited(limit = 10, period = 60) // Custom rate limiting annotation
        public ResponseEntity<ReportDTO> getYearlyReport() {
            return ResponseEntity.ok(reportService.generateYearlyReport());
        }
    }

