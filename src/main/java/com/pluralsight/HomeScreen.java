package com.pluralsight;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
import static com.pluralsight.DisplayLedger.*;
public class HomeScreen {
    // Created global variables

    // Define date and time format for displaying transactions
    public static DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");

    // Create a HashMap to store ledger entries (Transactions)
    public static HashMap<Integer, Ledger> ledgerHashMap = new HashMap<Integer, Ledger>();

    // Initialize a transaction identifier
    static int transactionId = 0;



    // Main Menu for the program
     public static void homeScreen() throws IOException {
        Scanner myScanner = new Scanner(System.in);
         String userInput;

         do {
             // Display the main menu options
             System.out.println("Welcome to Founder's Credit Union! Please select an option below" +
                     "\nD) Deposit" +
                     "\nP) Make a payment" +
                     "\nL) Ledger" +
                     "\nX) Exit");

             // Get the user's choice
             userInput = myScanner.next();
             myScanner.nextLine();

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
         while (!userInput.equals("X"));

     }

     // Function to add a deposit transaction
     public static void addDeposit() throws IOException {
         Scanner myScanner = new Scanner(System.in);

         // Create a BufferedWriter to write transactions to the file
         BufferedWriter bfWriter;
         bfWriter = new BufferedWriter(new FileWriter("src/main/resources/transactions.csv", true));

         // Gather details for the deposit
         while (true) {
             System.out.println("Description of transaction ");
             String description = myScanner.nextLine();
             System.out.println("Vendor ");
             String vendor = myScanner.nextLine();
             System.out.println("What is the amount ");
             double amount = myScanner.nextDouble();
             myScanner.nextLine();


             // Get current date and time for the deposit
             LocalDateTime currentDateTime = LocalDateTime.now();
             String formattedDateTime = currentDateTime.format(format);


             // Write the transactions details to the file
             bfWriter.write(formattedDateTime + "|" + description + "|" + vendor + "|" + amount);
             bfWriter.newLine();

             // Ask if the user wants to make another deposit
             System.out.println("Deposit has been made. Would you like to make another deposit? (Y/N): ");
             String userInput = myScanner.nextLine().toUpperCase();


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
         Scanner myScanner = new Scanner(System.in);

         // Create BufferedWriter to write payment transactions to file
         BufferedWriter bfWriter;
         bfWriter = new BufferedWriter(new FileWriter("src/main/resources/transactions.csv", true));

         // Create a loop that continues until the user decides to stop making payments
         // Ask user for details about payment
         while (true) {
             System.out.println("Description of transaction: ");
             String description = myScanner.nextLine();
             System.out.println("Vendor: ");
             String vendor = myScanner.nextLine();
             System.out.println("What is the amount: ");
             double amount = myScanner.nextDouble();
             myScanner.nextLine();

             // Mark the amount as negative to indicate it is a payment
             amount *= -1;

             // Get current date and time for the payment
             LocalDateTime currentDateTime = LocalDateTime.now();
             String formattedDateTime = currentDateTime.format(format);

             // Write the payment details to the file
             bfWriter.write(formattedDateTime + "|" + description + "|" + vendor + "|" + "|" + amount);
             bfWriter.newLine();

             // Ask if the user wants to make another payment
             System.out.println("Payment has been made. Would you like to make another payment? (Y/N): ");
             String userInput = myScanner.nextLine().toUpperCase();

             // Exit the payment loop if the user enters "N" (No)
             if (userInput.equals("N")) {
                 break;
             }
             // Close the BufferedWriter
             bfWriter.close();
         }
     }
}

