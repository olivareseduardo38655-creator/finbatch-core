package com.finbatch.model;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Accumulates execution statistics for the batch process.
 * Maintains thread-safe counters and total amount aggregations.
 * (Manual implementation without Lombok for explicit control)
 */
public class BatchSummary {
    private final AtomicInteger totalRecords = new AtomicInteger(0);
    private final AtomicInteger validRecords = new AtomicInteger(0);
    private final AtomicInteger errorRecords = new AtomicInteger(0);
    private BigDecimal totalAmount = BigDecimal.ZERO;

    // --- Business Logic Methods ---

    public void incrementTotal() {
        totalRecords.incrementAndGet();
    }

    public void incrementValid() {
        validRecords.incrementAndGet();
    }

    public void incrementError() {
        errorRecords.incrementAndGet();
    }

    public synchronized void addToTotalAmount(BigDecimal amount) {
        this.totalAmount = this.totalAmount.add(amount);
    }

    // --- Getters (Standard Java) ---

    public int getTotalRecords() {
        return totalRecords.get();
    }

    public int getValidRecords() {
        return validRecords.get();
    }

    public int getErrorRecords() {
        return errorRecords.get();
    }

    public synchronized BigDecimal getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "BatchSummary{" +
                "totalRecords=" + totalRecords +
                ", validRecords=" + validRecords +
                ", errorRecords=" + errorRecords +
                ", totalAmount=" + totalAmount +
                '}';
    }
}