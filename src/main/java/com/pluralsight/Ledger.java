package com.pluralsight;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
public class Ledger {
    // Declare instance variables to store lodger info
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    // Constructor for creating ledger entry
    public Ledger(LocalDate date, LocalTime time, String description, String vendor, double amount) {
       // Set the provided details into the ledger
       this.date = date;
       this.time = time;
       this.description = description;
       this.vendor = vendor;
       this.amount = amount;
    }
    // Getters to retrieve the provided details
    public LocalDate getDate() {
        return this.date;
    }
    public LocalTime getTime() {
        return this.time;
    }
    public String getDescription() {
        return this.description;
    }
    public String getVendor() {
        return this.vendor;
    }
    public double getAmount() {
        return this.amount;
    }
}
