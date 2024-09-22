package com.example.kiranastore.dto;

import lombok.Data;

@Data
public class ReportDTO {

        private double totalCredits;
        private double totalDebits;
        private double netFlow;

        public ReportDTO(double totalCredits, double totalDebits, double netFlow) {
            this.totalCredits = totalCredits;
            this.totalDebits = totalDebits;
            this.netFlow = netFlow;
        }

        // Getters and setters
        public double getTotalCredits() {
            return totalCredits;
        }

        public void setTotalCredits(double totalCredits) {
            this.totalCredits = totalCredits;
        }

        public double getTotalDebits() {
            return totalDebits;
        }

        public void setTotalDebits(double totalDebits) {
            this.totalDebits = totalDebits;
        }

        public double getNetFlow() {
            return netFlow;
        }

        public void setNetFlow(double netFlow) {
            this.netFlow = netFlow;
        }
    }

