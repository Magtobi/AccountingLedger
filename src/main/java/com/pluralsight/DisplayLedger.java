package com.pluralsight;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import static com.pluralsight.HomeScreen.*;
import static com.pluralsight.Ledger.*;

public class DisplayLedger {
    static Scanner myScanner = new Scanner(System.in);

    // Displays the ledger and manages user interaction
    public static void displayLedger() throws IOException {

        // Read transaction data from the file using the Reader class
        Reader.fileReader();
        String userInput;
        do {

            // Displays ledger menu
            System.out.println("These are your options" +
                    "\nA) All entries" +
                    "\nD) View deposits" +
                    "\nP) View payments" +
                    "\nR) Reports" +
                    "\nH) Home");

            userInput = myScanner.nextLine().trim();

            // Handles user input by calling different methods
            switch (userInput) {
                case "A":
                    displayAll(ledgerListByDate(ledgerList));
                    break;
                case "D":
                    displayDeposits(ledgerListByDate(ledgerList));
                    break;
                case "P":
                    displayPayments(ledgerListByDate(ledgerList));
                    break;
                case "R":
                    newReports(ledgerListByDate(ledgerList));
                    break;
                case "H":
                    homeScreen();
                default:
                    System.out.println("Try again! Enter a valid option");
                    break;
            }
        } while (!userInput.equals("H"));

    }
    // This method sorts the ledger entries by date and time
    public static ArrayList<Ledger> ledgerListByDate(ArrayList<Ledger> ledgerList) {
        ledgerList.sort((r1, r2) -> {
            LocalDateTime dateTime1 = LocalDateTime.of(r1.getDate(), r1.getTime());
            LocalDateTime dateTime2 = LocalDateTime.of(r2.getDate(), r2.getTime());
            return dateTime2.compareTo(dateTime1);
        });
        return ledgerList;
    }

    // This method displays all ledger entries
    public static void displayAll(ArrayList<Ledger> ledgerList) {
        for (Ledger r : ledgerList) {
            // Printing details of each ledger in this format
            System.out.printf("date|%s|time|%s|description|%s|vendor|%s|amount|$%.2f%n",
                    r.getDate(), r.getTime(), r.getDescription(), r.getVendor(), r.getAmount());
        }
    }

    // This method displays deposits from the ledger (positive amounts)
    public static void displayDeposits(ArrayList<Ledger> ledgerList) {
        for (Ledger r : ledgerList) {
            // Check if the ledger entry represents a deposit
            if (r.getAmount() > 0) {
                // Print details of transaction
                System.out.printf("date|%s|time|%s|description|%s|vendor|%s|amount|$%.2f%n",
                        r.getDate(), r.getTime(), r.getDescription(), r.getVendor(), r.getAmount());
            }
        }
    }

    // This method displays payments from the ledger (negative amounts)
    public static void displayPayments(ArrayList<Ledger> ledgerList) {
        for (Ledger r : ledgerList) {
            // Check if the ledger entry represents a payment
            if (r.getAmount() < 0) {
                // Print details of transaction
                System.out.printf("date|%s|time|%s|description|%s|vendor|%s|amount|$%.2f%n",
                        r.getDate(),r.getTime(), r.getDescription(), r.getVendor(), r.getAmount());
            }
        }
    }

    public static void newReports(ArrayList<Ledger> ledgerList) throws IOException {

        // Get the current date, month, year and the previous month and year
        LocalDate currentDate = LocalDate.now();
        Month currentMonth = currentDate.getMonth();
        YearMonth currentYearMonth = YearMonth.from(currentDate);
        YearMonth previousYearMonth = currentYearMonth.minusMonths(1);
        Year currentYear = Year.from(currentDate);
        Year previousYear = currentYear.minusYears(1);
        String userInput;
        String userChoice;
        do {
            // Display reports menu
            System.out.println("Reports: Select an option" +
                    "\n1) Month to Date" +
                    "\n2) Previous Month" +
                    "\n3) Year to Date" +
                    "\n4) Previous Year" +
                    "\n5) Search by Vendor" +
                    "\n6) Back to Ledger" +
                    "\n0) Ledger Screen");
            userInput = myScanner.nextLine().trim();

            switch (userInput) {
                case "1":
                    // Option 1: Display transactions for current month
                    for (Ledger r : ledgerList) {
                        LocalDate transactionDate = r.getDate();
                        Year transactionYear = Year.from(transactionDate);
                        Month transactionMonth = transactionDate.getMonth();
                        // Check if the transaction date is in the current month and year
                        if (transactionYear.equals(currentYear) && transactionMonth.equals(currentMonth)) {
                            // Print transaction details
                            System.out.printf("date|%s|time|%s|description|%s|vendor|%s|amount|$%.2f%n",
                                    transactionDate, r.getTime(), r.getDescription(), r.getVendor(), r.getAmount());
                        }
                    }
                    break;
                case "2":
                    // Option 2: Display transactions for previous month
                    for (Ledger r : ledgerList) {
                        LocalDate transactionDate = r.getDate();
                        YearMonth transactionYearMonth = YearMonth.from(transactionDate);
                        // Check if the transaction date is in the previous month and year
                        if (transactionYearMonth.equals(previousYearMonth)) {
                            // Print transaction details
                            System.out.printf("date|%s|time|%s|description|%s|vendor|%s|amount|$%.2f%n",
                                    transactionDate, r.getTime(), r.getDescription(), r.getVendor(), r.getAmount());
                        }
                    }
                    break;
                case "3":
                    // Option 3: Display transactions for the current year
                    for (Ledger r : ledgerList) {
                        LocalDate transactionDate = r.getDate();
                        Year transactionYear = Year.from(transactionDate);
                        // Check if the transaction date is in the current year
                        if (transactionYear.equals(currentYear)) {
                            // Print transaction details
                            System.out.printf("date|%s|time|%s|description|%s|vendor|%s|amount|$%.2f%n",
                                    transactionDate, r.getTime(), r.getDescription(), r.getVendor(), r.getAmount());
                        }
                    }
                    break;
                case "4":
                    // Option 4: Display transactions for the previous year
                    for (Ledger r : ledgerList) {
                        LocalDate transactionDate = r.getDate();
                        Year transactionYear = Year.from(transactionDate);
                        // Check if the transaction date is in the previous year.
                        if (transactionYear.equals(previousYear)) {
                            // Print transaction details
                            System.out.printf("date|%s|time|%s|description|%s|vendor|%s|amount|$%.2f%n",
                                    transactionDate, r.getTime(), r.getDescription(), r.getVendor(), r.getAmount());
                        }
                    }
                    break;
                case "5":
                    // Option 5: Search for transactions by vendor name
                    System.out.println("Input vendor");
                    userChoice = myScanner.nextLine().trim();
                    // Initialize a flag to track matches
                    boolean foundMatch = false;
                    // Iterate through each transaction in the ledger list
                    for (Ledger r : ledgerList) {
                        String vendorChoice = r.getVendor();
                        // Check if the vendor name matches the user's input
                        if (vendorChoice.equalsIgnoreCase(userChoice)) {
                            // Print transaction details
                            System.out.printf("date|%s|time|%s|description|%s|vendor|%s|amount|$%.2f%n",
                                    r.getDate(), r.getTime(), r.getDescription(), r.getVendor(), r.getAmount());
                            // Set the flag to true when a match is found
                            foundMatch = true;
                        }
                    }
                    // Notify the user if no matching vendor is found
                    if (!foundMatch) {
                        System.out.println("Vendor is not in this file");
                    }
                    break;
                case "6":
                    // Option 6: Go back to the ledger screen
                    displayLedger();
                    break;
                case "0":
                    // Option 0: Exit the reporting menu
                    break;
                default:
                    System.out.println("Invalid option! Input a valid option!");
                    break;
            }
        } while (!userInput.equals("0"));
    }
}