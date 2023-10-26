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
    static ArrayList<Ledger> ledgerList = new ArrayList<>();

    public static void displayLedger() throws IOException {

        Reader.fileReader();
        String userInput;
        do {
            System.out.println("These are your options" +
                    "\nA) All entries" +
                    "\nD) View deposits" +
                    "\nP) View payments" +
                    "\nR) Reports" +
                    "\nH) Home");

            userInput = myScanner.next();
            myScanner.nextLine().trim();

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
                    System.out.println("Try again! Enter a valid option A,D");
                    break;
            }
        } while (!userInput.equals("H"));

    }
    public static ArrayList<Ledger> ledgerListByDate(ArrayList<Ledger> ledgerlist) {
        Collections.sort(ledgerList, (r1, r2) -> {
            LocalDateTime dateTime1 = LocalDateTime.of(r1.getDate(), r1.getTime());
            LocalDateTime dateTime2 = LocalDateTime.of(r2.getDate(), r2.getTime());
            return dateTime1.compareTo(dateTime2);
        });
        return ledgerlist;
    }

    public static void displayAll(ArrayList<Ledger> ledgerList) throws IOException {
        for (Ledger r : ledgerList) {
            System.out.printf("date|%s|time|description|%s|vendor|%s|amount|$%>f%n",
                    r.getDate(), r.getTime(), r.getDescription(), r.getVendor(), r.getAmount());
        }
    }

    public static void displayDeposits(ArrayList<Ledger> ledgerList) throws IOException {
        for (Ledger r : ledgerList) {
            if (r.getAmount() > 0) {
                System.out.printf("date|%s|time|%s|description|%s|vendor|%s|amount|$%.2f%n",
                        r.getDate(), r.getTime(), r.getDescription(), r.getVendor(), r.getAmount());
            }
        }
    }

    public static void displayPayments(ArrayList<Ledger> ledgerList) throws IOException {
        for (Ledger r : ledgerList) {
            if (r.getAmount() < 0) {
                System.out.printf("date|%s|time|%s|description|%s|vendor|%s|amount|$%.2f%n",
                        r.getDate(),r.getTime(), r.getDescription(), r.getVendor(), r.getAmount());
            }
        }
    }

    public static void newReports(ArrayList<Ledger> ledgerList) throws IOException {
        LocalDate currentDate = LocalDate.now();
        Month currentMonth = currentDate.getMonth();
        YearMonth currentYearMonth = YearMonth.from(currentDate);
        YearMonth previousYearMonth = currentYearMonth.minusMonths(1);
        Year currentYear = Year.from(currentDate);
        Year previousYear = currentYear.minusYears(1);
        String userInput;
        String userChoice;
        do {
            System.out.println("Reports: Select an option" +
                    "\n1) Month to Date" +
                    "\n2) Previous Month" +
                    "\n3) Year to Date" +
                    "\n4) Previous Year" +
                    "\n5) Search by Vendor" +
                    "\n6) Back to Ledger" +
                    "\n0) Home Screen");
            userInput = myScanner.nextLine().trim();

            switch (userInput) {
                case "1":
                    for (Ledger r : ledgerList) {
                        LocalDate transactionDate = r.getDate();
                        Year transactionYear = Year.from(transactionDate);
                        Month transactionMonth = transactionDate.getMonth();
                        if (transactionYear.equals(currentYear) && transactionMonth.equals(currentMonth)) {
                            System.out.printf("date|%s|description|%s|vendor|%s|amount|$%.2f%n",
                                    transactionDate, r.getTime(), r.getDescription(), r.getVendor(), r.getAmount());
                        }
                    }
                    break;
                case "2":
                    for (Ledger r : ledgerList) {
                        LocalDate transactionDate = r.getDate();
                        YearMonth transactionYearMonth = YearMonth.from(transactionDate);
                        if (transactionYearMonth.equals(previousYearMonth)) {
                            System.out.printf("date|%s|description|%s|vendor|%s|amount|$%.2f%n",
                                    transactionDate, r.getTime(), r.getDescription(), r.getVendor(), r.getAmount());
                        }
                    }
                    break;
                case "3":
                    for (Ledger r : ledgerList) {
                        LocalDate transactionDate = r.getDate();
                        Year transactionYear = Year.from(transactionDate);
                        if (transactionYear.equals(currentYear)) {
                            System.out.printf("date|%s|description|%s|vendor|%s|amount|$%.2f%n",
                                    transactionDate, r.getTime(), r.getDescription(), r.getVendor(), r.getAmount());
                        }
                    }
                    break;
                case "4":
                    for (Ledger r : ledgerList) {
                        LocalDate transactionDate = r.getDate();
                        Year transactionYear = Year.from(transactionDate);
                        if (transactionYear.equals(previousYear)) {
                            System.out.printf("date|%s|description|%s|vendor|%s|amount|$%.2f%n",
                                    transactionDate, r.getTime(), r.getDescription(), r.getVendor(), r.getAmount());
                        }
                    }
                    break;
                case "5":
                    System.out.println("Input vendor");
                    userChoice = myScanner.nextLine().trim();
                    boolean foundMatch = false;
                    for (Ledger r : ledgerList) {
                        String vendorChoice = r.getVendor();
                        if (vendorChoice.equalsIgnoreCase(userChoice)) {
                            System.out.printf("date|%s|description|%s|vendor|%s|amount|$%.2f%n",
                                    r.getDate(), r.getTime(), r.getDescription(), r.getVendor(), r.getAmount());
                            foundMatch = true;
                        }
                    }
                    if (!foundMatch) {
                        System.out.println("Vendor is not in this file");
                    }
                    break;
                case "6":
                    displayLedger();
                    break;
                case "0":

                    break;
                default:
                    System.out.println("Invalid option! Input a valid option!");
                    break;
            }
        } while (!userInput.equals("0"));
    }
}