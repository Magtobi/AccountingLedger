package com.pluralsight;
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
       // Set the date and time of the transaction
       this.date = date;
       this.time = time;

       // Set a brief description of the transaction
       this.description = description;

       // Set the name of the vendor
       this.vendor = vendor;

       // Set the transaction amount. Positive for deposits and negative for payments
       this.amount = amount;
    }
    // Getters to retrieve the provided details
    // Get and return the date and time of the transaction
    public LocalDate getDate() {
        return this.date;
    }
    public LocalTime getTime() {
        return this.time;
    }

    // Get and return the description of transaction
    public String getDescription() {
        return this.description;
    }

    // Get and return name of vendor
    public String getVendor() {
        return this.vendor;
    }

    // Get and return transaction amount
    public double getAmount() {
        return this.amount;
    }
}
