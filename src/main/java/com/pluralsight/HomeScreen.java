package com.pluralsight;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
import static com.pluralsight.DisplayLedger.*;

public class HomeScreen {
    // Created global variables
    // Define date and time format for displaying transactions
    public static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static Scanner myScanner = new Scanner(System.in);

    // Create a HashMap to store ledger entries (Transactions)
    public static HashMap<Integer, Ledger> ledgerHashMap = new HashMap<Integer, Ledger>();
    public static ArrayList<Ledger> ledgerList = new ArrayList<>(ledgerHashMap.values());





    // Main Menu for the program
     public static void homeScreen() throws IOException {

         String userInput;

         do {
             // Display the main menu options
             System.out.println("Welcome to Founder's Credit Union! Please select an option below" +
                     "\nD) Deposit" +
                     "\nP) Make a payment" +
                     "\nL) Ledger" +
                     "\nX) Exit");

             // Get the user's choice
             userInput = myScanner.nextLine().trim();


             // Respond to user's choice
             switch (userInput) {
                 case "D":
                     addDeposit();
                     break;
                 case "P":
                     makePayment();
                     break;
                 case "L":
                     displayLedger();
                     break;
                 case "X":
                     // Exit the program
                     System.exit(0);
                 default:
                     System.out.println("Try again! Enter a valid option D,P,L,X");
             }
         }
         // Continue looping until "X" is selected
         while (true);

     }

     // Function to add a deposit transaction
     public static void addDeposit() throws IOException {

         // Create a BufferedWriter to write transactions to the file
         BufferedWriter bfWriter;
         bfWriter = new BufferedWriter(new FileWriter("src/main/resources/transactions.csv", true));

         // Gather details for the deposit
         while (true) {
             System.out.println("Description of transaction ");
             String description = myScanner.nextLine().trim();
             System.out.println("Vendor ");
             String vendor = myScanner.nextLine().trim();
             System.out.println("What is the amount ");
             double amount = myScanner.nextDouble();
             myScanner.nextLine().trim();

             // Get current date and time for the deposit
             String date = dateFormat.format(LocalDate.now());
             String time = timeFormat.format(LocalTime.now());

             // Write the transactions details to the file
             bfWriter.newLine();
             bfWriter.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount);

             // Ask if the user wants to make another deposit
             System.out.println("Deposit has been made. Would you like to make another deposit? (Y/N): ");
             String userInput = myScanner.nextLine().trim();

             // Exit the deposit loop if the user enters "N" (No)
             if (userInput.equals("N")) {
                 break;
             }
         }
         // Close BufferedWriter
         bfWriter.close();
     }

     // Function for making a payment
     public static void makePayment() throws IOException {


         // Create BufferedWriter to write payment transactions to file
         BufferedWriter bfWriter;
         bfWriter = new BufferedWriter(new FileWriter("src/main/resources/transactions.csv", true));

         // Create a loop that continues until the user decides to stop making payments
         // Ask user for details about payment
         while (true) {
             System.out.println("Description of transaction: ");
             String description = myScanner.nextLine().trim();
             System.out.println("Vendor: ");
             String vendor = myScanner.nextLine().trim();
             System.out.println("What is the amount: ");
             double amount = myScanner.nextDouble();
             myScanner.nextLine().trim();

             // Mark the amount as negative to indicate it is a payment
             amount *= -1;

             // Get current date and time for the payment
             String date = String.valueOf(LocalDate.now());
             String time = timeFormat.format(LocalTime.now());

             // Write the payment details to the file
             bfWriter.newLine();
             bfWriter.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount);


             // Ask if the user wants to make another payment
             System.out.println("Payment has been made. Would you like to make another payment? (Y/N): ");
             String userInput = myScanner.nextLine().trim();

             // Exit the payment loop if the user enters "N" (No)
             if (userInput.equals("N")) {
                 break;
             }
         }
         // Close BufferedWriter
         bfWriter.close();
     }
}

