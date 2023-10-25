package com.pluralsight;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import com.pluralsight.HomeScreen.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;

public class DisplayLedger {
    static Scanner myScanner = new Scanner(System.in);
    static List<Ledger> ledgerList = new ArrayList<>();

    public static void displayLedger() throws IOException {

        Reader.Reader();
        String userInput;
        do {
            System.out.println("These are your options" +
                    "\nA) All entries" +
                    "\nD) View deposits" +
                    "\nP) View payments" +
                    "\nR) Reports" +
                    "\nH) Home");
            displayMenu();
            userInput = myScanner.next();
            myScanner.nextLine();

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

    public static void displayAll(List<Ledger> ledgerList) throws IOException {
        for (Ledger r : ledgerList)
            System.out.printf("date|%s|time|description|%s|vendor|%s|amount|$%>f%n", r.getDateTime(), r.getDescription(), r.getVendor(), r.getAmount());
    }

    public static void displayDeposits(List<Ledger> ledgerList) throws IOException {
        for (Ledger r : ledgerList) {
            if (r.getAmount() > 0) {
                System.out.printf("date|%s|time|%s|description|%s|vendor|%s|amount|$%.2f%n",
                        r.getDateTime(), r.getDescription(), r.getVendor(), r.getAmount());
            }
        }
    }

    public static void displayPayments(List<Ledger> ledgerList) throws IOException {
        for (Ledger r : ledgerList) {
            if (r.getAmount() < 0) {
                System.out.println("date|%s|time|%s|description|%s|vendor|%s|amount|$%.2f%n");
            }
        }
    }

    public static List<Ledger> ledgerListByDate(List<Ledger> ledgerlist) {
        Collections.sort(ledgerList, (r1, r2) -> {
            LocalDateTime dateTime1 = LocalDateTime.of(r1.getDate(), r1.getTime());
            LocalDateTime dateTime2 = LocalDateTime.of(r2.getDate(), r2.getTime());
            return dateTime2.compareTo(dateTime1);
        });
        return ledgerlist;
    }
    public static void newReports(List<Ledger> ledgerList) throws IOException {
        LocalDate currentDate = LocalDate.now();

    }
}
