package com.pluralsight;
import java.time.LocalDateTime;
public class Ledger {
    // Declare instance variables to store lodger info
    private LocalDateTime dateTime;
    private String description;
    private String vendor;
    private double amount;

    // Constructor for creating ledger entry
    public Ledger(LocalDateTime dateTime, String description, String vendor, double amount) {
       // Set the provided details into the ledger
       this.dateTime = dateTime;
       this.description = description;
       this.vendor = vendor;
       this.amount = amount;
    }
    // Getters to retrieve the provided details
    public LocalDateTime getDateTime() {
        return this.dateTime;
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
